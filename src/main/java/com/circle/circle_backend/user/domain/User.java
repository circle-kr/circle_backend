package com.circle.circle_backend.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nickName;
    private String school;
    private String country;
    private String profileImage;

    @Builder
    public User(String email, String password, String firstName, String lastName, String nickName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
    }

}
