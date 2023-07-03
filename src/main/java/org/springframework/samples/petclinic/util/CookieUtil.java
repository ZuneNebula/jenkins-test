package org.springframework.samples.petclinic.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

@Component
public class CookieUtil {
    public CookieUtil() {
    }

    public static Cookie create(HttpServletResponse httpServletResponse, String name, String value, Boolean secure, Integer maxAge, String domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(secure);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        return cookie;
    }

    public static void clearCookie(HttpServletResponse httpServletResponse, String name, String domain) {
        Cookie cookie = new Cookie(name, (String)null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setDomain(domain);
        httpServletResponse.addCookie(cookie);
    }

    public static String getValue(HttpServletRequest httpServletRequest, String name) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
        return cookie != null ? cookie.getValue() : null;
    }
}
