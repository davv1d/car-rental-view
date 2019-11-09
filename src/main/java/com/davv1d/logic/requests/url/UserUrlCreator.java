package com.davv1d.logic.requests.url;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UserUrlCreator extends EndpointsCarRental{

    public URI getUsersRootUri() {
       return createUriWithoutParameters(userUrl);
    }

    public URI getUserByUsernameUri(String username) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("username", username);
        return createUriWithParams(userUrl, params);
    }
}
