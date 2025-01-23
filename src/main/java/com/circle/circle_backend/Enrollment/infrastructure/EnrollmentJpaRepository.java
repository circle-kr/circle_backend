package com.circle.circle_backend.Enrollment.infrastructure;

import com.circle.circle_backend.Enrollment.infrastructure.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnrollmentJpaRepository extends JpaRepository<EnrollmentEntity, Long> {

    EnrollmentEntity findByUserIdAndCircleId(Long userId, Long circleId);
}
