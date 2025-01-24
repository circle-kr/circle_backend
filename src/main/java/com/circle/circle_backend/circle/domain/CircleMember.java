package com.circle.circle_backend.circle.domain;

import com.circle.circle_backend.circle.domain.enums.UserRole;
import com.circle.circle_backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CircleMember {

    private Circle circle;
    private User user;
    private UserRole userRole;

    public static CircleMember of(Circle circle, User user, UserRole userRole) {
        return CircleMember.builder()
                .circle(circle)
                .user(user)
                .userRole(userRole)
                .build();
    }


    public boolean isAdmin() {
        return userRole == UserRole.ADMIN;
    }
}
