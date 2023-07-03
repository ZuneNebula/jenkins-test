package org.springframework.samples.petclinic.rest.controller;

import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.IGoogleService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.util.CookieUtil;
import org.springframework.samples.petclinic.util.JWTUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/authorize")
public class OAuthController {
    @Value("${home_page_url}")
    String homePageUrl;

    @Value("${user_redirect_url}")
    String userRedirectUrl;
    @Autowired
    IGoogleService googleService;
    @Autowired
    UserService userService;

    @Value("${logout_redirect_url}")
    String logOutRedirectUrl;

    public OAuthController() {
    }

    @GetMapping({"/googlelogin"})
    public RedirectView googleUserLogin() {
        RedirectView redirectview = new RedirectView();
        String url = this.googleService.googlelogin(this.userRedirectUrl);
        redirectview.setUrl(url);
        return redirectview;
    }
    @GetMapping({"/logout"})
    public RedirectView googleLogOut(HttpServletResponse res){
        RedirectView redirectView = new RedirectView();
        CookieUtil.clearCookie(res, "JWT-TOKEN", "localhost");
        redirectView.setUrl(logOutRedirectUrl); //read from config server later
        return redirectView;
    }

    @GetMapping({"/complete"})
    public RedirectView googleUser(@RequestParam("code") String code, HttpServletResponse res) throws ParseException {
        String accessToken = this.googleService.getGoogleAccessToken(code, this.userRedirectUrl);
        System.out.println("accessToken: " + accessToken);
        User user = this.googleService.getGoogleUserProfile(accessToken);
        //save user in repo
        try {
            System.out.println("USER:: " + user.toString());
            if(userService.findByUsername(user.getUsername()) == null)
            {
                this.userService.saveUser(user);
            }
        } catch (Exception var8) {
            PrintStream var10000 = System.out;
            LocalDateTime var10001 = LocalDateTime.now();
            var10000.println("In google method " + var10001 + " " + var8.getMessage());
        }
        //get saved user from repo
        User repoUser = null;
        try{
            repoUser = userService.findByUsername(user.getUsername());
        }
        catch (Exception ex){
            ex.getMessage();
        }

        String jwtToken = JWTUtil.addJWTToken(res, repoUser);
        CookieUtil.create(res, "JWT-TOKEN", jwtToken, false, -1, "localhost");
        RedirectView redirectview = new RedirectView();
        redirectview.setUrl(this.homePageUrl);
        return redirectview;
    }
}
