package com.circle.circle_backend.common.response.responseEnum;

import com.circle.circle_backend.common.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorResponseEnum implements Response {

    CIRCLE_NOT_FOUND(HttpStatus.NOT_FOUND, "Circle Cannot Be Found"),
    CIRCLE_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "Circle Member Cannot Be Found"),
    ENROLLMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Enrollment Cannot Be Found"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Cannot Be Found"),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized User");

    private final HttpStatus httpStatus;
    private final String message;
}
