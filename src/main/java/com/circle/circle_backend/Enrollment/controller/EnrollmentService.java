package com.circle.circle_backend.Enrollment.controller;

import com.circle.circle_backend.Enrollment.domain.Enrollment;
import com.circle.circle_backend.Enrollment.infrastructure.entity.EnrollmentEntity;
import com.circle.circle_backend.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface EnrollmentService {
    Enrollment createAndUpdate(User user, Long circleId);

    Optional<EnrollmentEntity> getEnrollmentState(User user, Long circleId);

    Optional<List<EnrollmentEntity>> getEnrollments(User user);
}
