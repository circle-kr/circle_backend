package com.circle.circle_backend.common.exception;

import com.circle.circle_backend.common.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class CustomException extends RuntimeException{
    private final Response response;
}
