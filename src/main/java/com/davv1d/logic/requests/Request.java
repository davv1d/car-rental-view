package com.davv1d.logic.requests;

import com.davv1d.logic.config.AuthHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

public abstract class Request {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AuthHeader authHeader;

    <U, T> ResponseEntity<U> sendRequestWithAuthorize(URI uri, HttpMethod method, Class<U> classDesc, T body) {
        Optional<HttpHeaders> authHeader = this.authHeader.getAuthHeader();
        if (authHeader.isPresent()) {
            HttpEntity<T> entity = new HttpEntity<>(body, authHeader.get());
            return restTemplate.exchange(uri, method, entity, classDesc);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    <U, T> ResponseEntity<U> sendRequestWithoutAuthorize(URI uri, HttpMethod method, Class<U> classDesc, T body) {
        HttpEntity<T> entity = new HttpEntity<>(body);
        return restTemplate.exchange(uri, method, entity, classDesc);
    }
}
