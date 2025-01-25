package com.circle.circle_backend.enrollment.service;

import com.circle.circle_backend.enrollment.domain.Enrollment;
import com.circle.circle_backend.enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.enrollment.infrastructure.entity.EnrollmentEntity;

import java.util.List;
import java.util.Optional;


public interface EnrollmentRepository {
   Optional<EnrollmentEntity> findByUserIdAndCircleId(Long userId, Long circleId);

   EnrollmentEntity save(Enrollment enrollment);

   List<EnrollmentEntity> findByCircleIdInAndEnrollmentState(List<Long> circleIds, EnrollmentState enrollmentState);

   Optional<EnrollmentEntity> findById(Long enrollmentId);
}
