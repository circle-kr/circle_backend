package com.circle.circle_backend.user.controller;

import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.domain.enums.Language;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MyInfoResponse {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String nickname;
    private final String school;
    private final String major;
    private final String country;
    private final List<Language> languages;
    private final String profileImage;
    private final String bio;

    public static MyInfoResponse from(User user) {
        return MyInfoResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .nickname(user.getNickname())
                .school(user.getSchool())
                .major(user.getMajor())
                .country(user.getCountry())
                .languages(user.getLanguages())
                .profileImage(user.getProfileImage())
                .bio(user.getBio())
                .build();
    }
}
