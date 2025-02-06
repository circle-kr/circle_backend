package com.circle.circle_backend.user.dto.response;

import com.circle.circle_backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class UserResponse {
    private final Long id;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String nickname;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .nickname(user.getNickname())
                .build();
    }

}
