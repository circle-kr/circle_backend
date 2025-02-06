package com.circle.circle_backend.common.response;

import org.springframework.http.HttpStatus;

public interface Response {
    HttpStatus getHttpStatus();
    String getMessage();
}
