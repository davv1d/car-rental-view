package com.davv1d.logic.config;

import com.davv1d.logic.cookie.CookieMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Component
public class AuthHeader {

    public static final String TOKEN_COOKIE = "token";
    private static final String TOKEN_TYPE = "Bearer ";
    @Autowired
    private CookieMaker cookieMaker;

    public Optional<HttpHeaders> getAuthHeader() {
        HttpHeaders header = new HttpHeaders();
        Optional<Cookie> optionalCookie = cookieMaker.getCookie(TOKEN_COOKIE);
        if (optionalCookie.isPresent()) {
            Cookie cookie = optionalCookie.get();
            header.add(HttpHeaders.AUTHORIZATION, TOKEN_TYPE + cookie.getValue());
            return Optional.of(header);
        } else {
            return Optional.empty();
        }
    }
}
