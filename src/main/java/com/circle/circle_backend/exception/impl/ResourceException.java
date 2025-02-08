package com.circle.circle_backend.exception.impl;

import com.circle.circle_backend.common.response.Response;
import com.circle.circle_backend.exception.CustomException;

    public class ResourceException extends CustomException {

    public ResourceException(Response response) {
        super(response);
    }
}
