package com.circle.circle_backend.enrollment.controller.dto.response;

import com.circle.circle_backend.enrollment.domain.Enrollment;
import com.circle.circle_backend.enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EnrollmentResponse {
    private Long id;
    private Circle circle;
    private User user;
    private EnrollmentState enrollmentState;

    public static EnrollmentResponse from(Enrollment enrollment) {
        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .circle(enrollment.getCircle())
                .user(enrollment.getUser())
                .enrollmentState(enrollment.getEnrollmentState())
                .build();
    }
}
