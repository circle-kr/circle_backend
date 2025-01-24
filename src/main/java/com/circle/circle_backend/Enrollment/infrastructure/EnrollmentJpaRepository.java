package com.circle.circle_backend.Enrollment.infrastructure;

import com.circle.circle_backend.Enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.Enrollment.infrastructure.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EnrollmentJpaRepository extends JpaRepository<EnrollmentEntity, Long> {

    Optional<EnrollmentEntity> findByUserIdAndCircleId(Long userId, Long circleId);

    List<EnrollmentEntity> findByCircleIdInAndEnrollmentState(List<Long> circleIds, EnrollmentState enrollmentState);

}
