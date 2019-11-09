package com.davv1d.logic.domain.login;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponse {
    private String token;
    private String type = "Bearer";
    private String role;
    public LoginResponse(String token) {
        this.token = token;
    }

    public LoginResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }
}
