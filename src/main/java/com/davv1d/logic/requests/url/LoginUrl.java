package com.davv1d.logic.requests.url;

import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class LoginUrl extends EndpointsCarRental {
    public URI getSignInUri() {
        return createUriWithoutParameters(signInUrl);
    }

    public URI getSingUpUrl() {
        return createUriWithoutParameters(singUpUrl);
    }
}
