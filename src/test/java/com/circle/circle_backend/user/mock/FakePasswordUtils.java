package com.circle.circle_backend.user.mock;

import com.circle.circle_backend.security.utils.PasswordUtils;

public class FakePasswordUtils implements PasswordUtils {

    @Override
    public String encode(String rawPassword) {
        return "encoded" + rawPassword;
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        if (encodedPassword.equals(encode(rawPassword))) {
            return true;
        }
        return false;
    }
}
