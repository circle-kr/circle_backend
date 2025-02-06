package com.circle.circle_backend.user.dto.response;

import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.domain.enums.Language;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserInfoResponse {
    private final Long id;
    private final String profileImage;
    private final String nickname;
    private final String school;
    private final String major;
    private final String country;
    private final List<Language> languages;
    private final String bio;

    public static UserInfoResponse from(User user) {
        return UserInfoResponse.builder()
                .id(user.getId())
                .profileImage(user.getProfileImage())
                .nickname(user.getNickname())
                .school(user.getSchool())
                .major(user.getMajor())
                .country(user.getCountry())
                .languages(user.getLanguages())
                .bio(user.getBio())
                .build();
    }
}
