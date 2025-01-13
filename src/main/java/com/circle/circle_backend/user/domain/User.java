package com.circle.circle_backend.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private final Long id;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String nickname;
    private final String school;
    private final String country;
    private final String profileImage;

    @Builder
    public User(Long id, String email, String password, String firstName, String lastName, String nickname, String school, String country, String profileImage) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.school = school;
        this.country = country;
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
