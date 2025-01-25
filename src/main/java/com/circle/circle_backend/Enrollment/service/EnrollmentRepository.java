package com.circle.circle_backend.Enrollment.service;

import com.circle.circle_backend.Enrollment.domain.Enrollment;
import com.circle.circle_backend.Enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.Enrollment.infrastructure.entity.EnrollmentEntity;

import java.util.List;
import java.util.Optional;


public interface EnrollmentRepository {
   Optional<EnrollmentEntity> findByUserIdAndCircleId(Long userId, Long circleId);

   EnrollmentEntity save(Enrollment enrollment);

   List<EnrollmentEntity> findByCircleIdInAndEnrollmentState(List<Long> circleIds, EnrollmentState enrollmentState);

   Optional<EnrollmentEntity> findById(Long enrollmentId);
}
