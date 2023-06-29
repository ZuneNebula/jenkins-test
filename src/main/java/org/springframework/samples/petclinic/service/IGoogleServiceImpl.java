package org.springframework.samples.petclinic.service;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.model.Role;
import org.springframework.samples.petclinic.model.User;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;

@Service
public class IGoogleServiceImpl implements IGoogleService{

    @Value("${spring.social.google.app-id}")
    private String googleId;
    @Value("${spring.social.google.app-secret}")
    private String googleSecret;
    private static final String SystemData = "SYSTEM";

    public IGoogleServiceImpl() {
    }

    private GoogleConnectionFactory createGoogleConnection() {
        return new GoogleConnectionFactory(this.googleId, this.googleSecret);
    }

    public String googlelogin(String redirectUrl) {
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri(redirectUrl);
        parameters.setScope("profile email");
        return this.createGoogleConnection().getOAuthOperations().buildAuthenticateUrl(parameters);
    }

    public String getGoogleAccessToken(String code, String redirectUrl) {
        return this.createGoogleConnection().getOAuthOperations().exchangeForAccess(code, redirectUrl, (MultiValueMap)null).getAccessToken();
    }

    public User getGoogleUserProfile(String accessToken) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String profileData = (String)restTemplate.getForObject("https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=" + accessToken, String.class, new Object[0]);
        System.out.println("profileData" + profileData.getClass());
        System.out.println("profileData" + profileData);
        JSONParser parser = new JSONParser();
        JSONObject profileObj = (JSONObject)parser.parse(profileData);
        User user = new User();
        System.out.println("profileobj" + profileObj.getClass());
        System.out.println("name" + profileObj.get("name"));

        //user.setUsername(profileObj.get("email").toString());
        String name = profileObj.get("name").toString();
        String[] fullName = name.split(" ", 2);
        user.setUsername(fullName[0]);
        user.setEnabled(true);
        Role role = new Role();
        role.setUser(user);
        role.setName("ROLE_USER");
        HashSet<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword("password");
        return user;
    }
}
