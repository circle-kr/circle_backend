package com.circle.circle_backend.enrollment.infrastructure;

import com.circle.circle_backend.enrollment.domain.Enrollment;
import com.circle.circle_backend.enrollment.service.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    private final EnrollmentJpaRepository enrollmentJpaRepository;

    @Override
    public Optional<Enrollment> findByUserIdAndCircleId(Long userId, Long circleId) {
        return enrollmentJpaRepository.findByUserIdAndCircleId(userId, circleId);
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        return enrollmentJpaRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> findPendingByCircleIds(List<Long> circleIds) {
        return enrollmentJpaRepository.findPendingByCircleIds(circleIds);
    }

    @Override
    public Optional<Enrollment> findById(Long enrollmentId) {
        return enrollmentJpaRepository.findById(enrollmentId);
    }
}
