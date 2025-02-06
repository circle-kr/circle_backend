package com.circle.circle_backend.common.response.responseEnum;

import com.circle.circle_backend.common.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessResponseEnum implements Response {

    // circle
    CREATE_CIRCLE(HttpStatus.CREATED, "Circle Is Created Successfully"),
    READ_CIRCLE_LIST(HttpStatus.OK, "Circle List Is Loaded Successfully"),
    READ_CIRCLE_INFO(HttpStatus.OK, "Circle Info Is Loaded Successfully"),
    UPDATE_CIRCLE_INFO(HttpStatus.OK, "Circle Info Is Updated Successfully"),

    // circle member
    READ_CIRCLE_MEMBER_LIST(HttpStatus.OK, "Circle Member List Is Loaded Successfully"),

    // enrollment
    CREATE_UPDATE_ENROLLMENT(HttpStatus.CREATED, "Enrollment Is Created Successfully"),
    UPDATE_ENROLLMENT(HttpStatus.OK, "Enrollment Is Updated Successfully"),
    READ_ENROLLMENT_STATE(HttpStatus.OK, "Enrollment State Is Loaded Successfully"),
    READ_ENROLLMENT_LIST(HttpStatus.OK, "Enrollment List Is Loaded Successfully"),

    // user
    CREATE_USER(HttpStatus.CREATED, "User Is Created Successfully"),
    PATCH_USER(HttpStatus.OK, "User Is Patched Successfully"),
    READ_MY_INFO(HttpStatus.OK, "My Info Is Loaded Successfully"),
    READ_USER_INFO(HttpStatus.OK, "User Info Is Loaded Successfully");

    private final HttpStatus httpStatus;
    private final String message;

}
