package com.circle.circle_backend.enrollment.dto.response;

import com.circle.circle_backend.enrollment.domain.Enrollment;
import com.circle.circle_backend.enrollment.domain.enums.EnrollmentState;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EnrollmentResponse {
    private Long id;
    private Long circleId;
    private Long userId;
    private EnrollmentState enrollmentState;

    public static EnrollmentResponse from(Enrollment enrollment) {
        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .circleId(enrollment.getCircle().getId())
                .userId(enrollment.getUser().getId())
                .enrollmentState(enrollment.getEnrollmentState())
                .build();
    }
}
