package com.circle.circle_backend.exception.impl;

import com.circle.circle_backend.common.response.Response;
import com.circle.circle_backend.exception.CustomException;

public class AuthException extends CustomException {

    public AuthException(Response response) {
        super(response);
    }
}
