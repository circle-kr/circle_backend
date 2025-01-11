package com.circle.circle_backend.user.controller;

import com.circle.circle_backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    private Long id;
    private String email;
    private String nickname;

    // FIXME: 응답값 수정 필요
    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }

}
