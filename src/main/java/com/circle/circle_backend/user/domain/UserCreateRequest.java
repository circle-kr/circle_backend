package com.circle.circle_backend.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCreateRequest {
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private String password;
}
