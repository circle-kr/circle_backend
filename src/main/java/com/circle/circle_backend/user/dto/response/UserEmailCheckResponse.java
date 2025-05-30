package com.circle.circle_backend.user.dto.response;

import com.circle.circle_backend.user.dto.request.UserEmailCheckRequest;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserEmailCheckResponse {
    private final String email;

    public static UserEmailCheckResponse from(UserEmailCheckRequest userEmailCheckRequest) {
        return UserEmailCheckResponse.builder()
                .email(userEmailCheckRequest.getEmail())
                .build();
    }
}
