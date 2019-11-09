package com.davv1d.logic.cookie;

import com.davv1d.logic.config.AuthHeader;
import com.davv1d.logic.domain.login.LoginResponse;
import com.vaadin.flow.server.VaadinService;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Component
public class CookieMaker {
    public void createCookie(LoginResponse loginResponse) {
        Cookie cookieToken = new Cookie(AuthHeader.TOKEN_COOKIE, loginResponse.getToken());
        cookieToken.setMaxAge(3600);
        VaadinService.getCurrentResponse().addCookie(cookieToken);
    }

    public Optional<Cookie> getCookie(String name) {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return Optional.of(cookie);
            }
        }
        return Optional.empty();
    }

    public void removeCookie() {
        Optional<Cookie> cookie1 = getCookie(AuthHeader.TOKEN_COOKIE);
        cookie1.ifPresent(cookie -> {
            cookie.setValue("");
            VaadinService.getCurrentResponse().addCookie(cookie);
        });
    }
}
