package com.circle.circle_backend.common.domain;

import com.circle.circle_backend.circleMember.domain.enums.UserRole;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(UserRole userRole) {
        super(userRole + "만 접근할 수 있습니다.");
    }
}
