package com.circle.circle_backend.security.filter.port;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
