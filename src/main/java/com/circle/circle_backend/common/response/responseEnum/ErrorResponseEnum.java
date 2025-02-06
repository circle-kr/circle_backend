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

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized User"),

    // auth
    AUTHENTICATION_IO_EXCEPTION(HttpStatus.BAD_REQUEST, "Client Send Bad Request"),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Refresh Token Cannot Be Found"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Expired JWT Token. Login Again Is Needed."),
    UNEXPECTED_AUTH_ERROR(HttpStatus.UNAUTHORIZED, "Unexpected Authentication Error"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid Token"),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "Unsupported Token"),;

    private final HttpStatus httpStatus;
    private final String message;
}
