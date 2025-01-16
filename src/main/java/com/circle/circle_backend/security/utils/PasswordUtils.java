package com.circle.circle_backend.security.utils;

public interface PasswordUtils {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}

