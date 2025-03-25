package com.circle.circle_backend.enrollment.service;

import com.circle.circle_backend.enrollment.domain.Enrollment;

import java.util.List;
import java.util.Optional;


public interface EnrollmentRepository {
   Optional<Enrollment> findByUserIdAndCircleId(Long userId, Long circleId);

   Enrollment save(Enrollment enrollment);

   List<Enrollment> findPendingByCircleIds(List<Long> circleIds);

   Optional<Enrollment> findById(Long enrollmentId);
}
