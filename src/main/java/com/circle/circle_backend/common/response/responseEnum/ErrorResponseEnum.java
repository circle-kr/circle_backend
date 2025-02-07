package com.circle.circle_backend.common.response.responseEnum;

import com.circle.circle_backend.common.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorResponseEnum implements Response {

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource Cannot Be Found"),
    DUPLICATED_RESOURCE(HttpStatus.CONFLICT, "Duplicated Resource"),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized User"),

    // Response Dto 유효성 검사
    RESPONSE_NOT_VALID(HttpStatus.BAD_REQUEST, "Response Is Not Valid"),

    // auth
    AUTHENTICATION_IO_EXCEPTION(HttpStatus.BAD_REQUEST, "Client Send Bad Request"),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Refresh Token Cannot Be Found"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Expired JWT Token. Login Again Is Needed."),
    UNEXPECTED_AUTH_ERROR(HttpStatus.UNAUTHORIZED, "Unexpected Authentication Error"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid Token"),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "Unsupported Token");

    private final HttpStatus httpStatus;
    private final String message;
}
