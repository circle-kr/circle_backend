package com.circle.circle_backend.enrollment.infrastructure;

import com.circle.circle_backend.enrollment.domain.Enrollment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface EnrollmentJpaRepository extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByUserIdAndCircleId(Long userId, Long circleId);

    @Query("SELECT e FROM Enrollment e WHERE e.circle.id IN :circleIds AND e.enrollmentState = 'PENDING'")
    List<Enrollment> findPendingByCircleIds(@Param("circleIds") List<Long> circleIds);

}
