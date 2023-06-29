package org.springframework.samples.petclinic.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    static final long EXPIRATIONTIME = 3600000L;
    static String SIGNINGKEY = "secret";
    static final String PREFIX = "Bearer";

    //add code to create JWT token
    public static String addJWTToken(HttpServletResponse res, User user) {
        Claims claims = Jwts.claims();
        claims.put("username", user.getUsername());
        String jwtToken = Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + 3600000L)).signWith(SignatureAlgorithm.HS512, SIGNINGKEY).compact();
        return jwtToken;
    }
}
