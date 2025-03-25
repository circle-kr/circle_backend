package com.circle.circle_backend.circleMember.dto.response;

import com.circle.circle_backend.circleMember.domain.CircleMember;
import com.circle.circle_backend.circleMember.domain.enums.UserRole;
import com.circle.circle_backend.user.domain.enums.Language;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class CircleMemberResponse {
    private String nickname;
    private String profileImage;
    private String major;
    private List<Language> languages;
    private UserRole userRole;

    public static CircleMemberResponse from(CircleMember circleMember) {
        return CircleMemberResponse.builder()
                .nickname(circleMember.getUser().getNickname())
                .profileImage(circleMember.getUser().getNickname())
                .major(circleMember.getUser().getMajor())
                .languages(circleMember.getUser().getLanguages())
                .userRole(circleMember.getUserRole())
                .build();
    }
}
