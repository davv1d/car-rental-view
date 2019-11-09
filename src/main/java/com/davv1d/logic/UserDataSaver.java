package com.davv1d.logic;

import com.davv1d.logic.cookie.CookieMaker;
import com.davv1d.logic.domain.login.Login;
import com.davv1d.logic.domain.login.LoginResponse;
import com.davv1d.logic.requests.LoginRequestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserDataSaver {
    @Autowired
    private LoginRequestSender loginRequestSender;

    @Autowired
    private CookieMaker cookieMaker;

    public ResponseEntity<String> saveUserLoginData(final Login login) {
        ResponseEntity<LoginResponse> loginResponse = loginRequestSender.signIn(login);
        if (!loginResponse.getStatusCode().isError() && loginResponse.getBody() != null) {
            LoginResponse loginResponse1 = loginResponse.getBody();
            cookieMaker.createCookie(loginResponse1);
            return ResponseEntity.ok().body(loginResponse1.getRole());
        } else {
            return ResponseEntity.status(loginResponse.getStatusCode().value()).build();
        }
    }
}
