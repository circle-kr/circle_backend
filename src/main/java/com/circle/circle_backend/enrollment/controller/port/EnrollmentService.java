package com.circle.circle_backend.enrollment.controller.port;

import com.circle.circle_backend.enrollment.domain.Enrollment;
import com.circle.circle_backend.enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.enrollment.infrastructure.entity.EnrollmentEntity;
import com.circle.circle_backend.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface EnrollmentService {
    Enrollment createAndUpdate(User user, Long circleId);

    Optional<EnrollmentEntity> readEnrollmentState(User user, Long circleId);

    Optional<List<EnrollmentEntity>> readEnrollmentList(User user);

    Enrollment acceptOrDecline(User user, Long enrollmentId, EnrollmentState enrollmentState);
}
