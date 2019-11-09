package com.davv1d.logic.domain.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SingUp {
    private String username;
    private String password;
    private String email;
    private String role;
}
