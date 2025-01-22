package com.circle.circle_backend.Enrollment.service;

import com.circle.circle_backend.Enrollment.domain.Enrollment;
import com.circle.circle_backend.Enrollment.infrastructure.entity.EnrollmentEntity;


public interface EnrollmentRepository {
   EnrollmentEntity findByUserIdAndCircleId(Long userId, Long circleId);

    EnrollmentEntity save(Enrollment enrollment);

}
