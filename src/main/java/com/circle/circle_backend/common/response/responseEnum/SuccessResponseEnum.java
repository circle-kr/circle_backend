package com.circle.circle_backend.common.response.responseEnum;

import com.circle.circle_backend.common.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessResponseEnum implements Response {
    CREATE_RESOURCES(HttpStatus.CREATED, "Resource Is Created Successfully"),
    UPDATE_RESOURCES(HttpStatus.OK, "Resource Is Updated Successfully"),
    READ_RESOURCES(HttpStatus.OK, "Resource Is Loaded Successfully");

    private final HttpStatus httpStatus;
    private final String message;
}
