package com.circle.circle_backend.Enrollment.infrastructure;

import com.circle.circle_backend.Enrollment.infrastructure.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EnrollmentJpaRepository extends JpaRepository<EnrollmentEntity, Long> {

    Optional<EnrollmentEntity> findByUserIdAndCircleId(Long userId, Long circleId);
}
