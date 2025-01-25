package com.circle.circle_backend.enrollment.domain;

import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Enrollment {

    Long id;

    Circle circle;

    User user;

    EnrollmentState enrollmentState;

    @Builder
    public Enrollment(Long id, Circle circle, User user, EnrollmentState enrollmentState) {
        this.id = id;
        this.circle = circle;
        this.user = user;
        this.enrollmentState = enrollmentState;
    }
}
