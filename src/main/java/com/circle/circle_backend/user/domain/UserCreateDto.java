package com.circle.circle_backend.user.domain;

import lombok.Getter;

@Getter
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private String password;
}
