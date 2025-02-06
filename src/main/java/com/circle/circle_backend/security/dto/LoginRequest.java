package com.circle.circle_backend.security.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
