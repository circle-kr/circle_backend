package com.circle.circle_backend.user.dto.request;

import com.circle.circle_backend.user.domain.enums.Language;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserPatchRequest {
    private String firstName;
    private String lastName;
    private String nickname;
    private String school;
    private String major;
    private String country;
    private List<Language> languages;
    private String profileImage;
    private String bio;
}
