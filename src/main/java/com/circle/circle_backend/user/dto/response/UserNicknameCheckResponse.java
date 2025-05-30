package com.circle.circle_backend.user.dto.response;

import com.circle.circle_backend.user.dto.request.UserNicknameCheckRequest;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserNicknameCheckResponse {
    private final String nickname;

    public static UserNicknameCheckResponse from(UserNicknameCheckRequest userNicknameCheckRequest) {
        return UserNicknameCheckResponse.builder()
                .nickname(userNicknameCheckRequest.getNickname())
                .build();
    }
}
