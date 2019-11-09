package com.davv1d.logic.requests;

import com.davv1d.logic.domain.login.Login;
import com.davv1d.logic.domain.login.LoginResponse;
import com.davv1d.logic.domain.login.SingUp;
import com.davv1d.logic.requests.url.LoginUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class LoginRequestSender extends Request{
    @Autowired
    private LoginUrl loginUrl;

    public ResponseEntity<LoginResponse> signIn(Login login) {
        URI signInUri = loginUrl.getSignInUri();
        return sendRequestWithoutAuthorize(signInUri, HttpMethod.POST, LoginResponse.class, login);
    }

    public ResponseEntity<String> signUp(SingUp singUp) {
        URI singUpUrl = loginUrl.getSingUpUrl();
        return sendRequestWithoutAuthorize(singUpUrl, HttpMethod.POST, String.class, singUp);
    }
}
