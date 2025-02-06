package com.circle.circle_backend.user.domain;

import com.circle.circle_backend.user.domain.enums.Language;
import com.circle.circle_backend.user.dto.request.UserCreateRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class User {

    private final Long id;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String nickname;
    private final String school;
    private final String major;
    private final String country;
    private final List<Language> languages;
    private final String bio;
    private final String profileImage;

    @Builder
    public User(Long id, String email, String password, String firstName, String lastName, String nickname, String school, String major, String country, List<Language> languages, String bio, String profileImage) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.school = school;
        this.major = major;
        this.country = country;
        this.languages = languages;
        this.bio = bio;
        this.profileImage = profileImage;
    }

    public static User from(UserCreateRequest userCreateRequest, String encodedPassword) {
        return User.builder()
                .firstName(userCreateRequest.getFirstName())
                .lastName(userCreateRequest.getLastName())
                .email(userCreateRequest.getEmail())
                .password(encodedPassword)
                .nickname(userCreateRequest.getNickname())
                .build();
    }

}
