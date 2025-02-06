package com.circle.circle_backend.circleMember.controller.dto.response;

import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circleMember.domain.CircleMember;
import com.circle.circle_backend.circleMember.domain.enums.UserRole;
import com.circle.circle_backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CircleMemberResponse {
    private Circle circle;
    private User user;
    private UserRole userRole;

    public static CircleMemberResponse from(CircleMember circleMember) {
        return CircleMemberResponse.builder()
                .circle(circleMember.getCircle())
                .user(circleMember.getUser())
                .userRole(circleMember.getUserRole())
                .build();

    }
}
