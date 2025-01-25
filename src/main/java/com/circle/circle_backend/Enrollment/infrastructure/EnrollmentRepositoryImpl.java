package com.circle.circle_backend.Enrollment.infrastructure;

import com.circle.circle_backend.Enrollment.domain.Enrollment;
import com.circle.circle_backend.Enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.Enrollment.infrastructure.entity.EnrollmentEntity;
import com.circle.circle_backend.Enrollment.service.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    private final EnrollmentJpaRepository enrollmentJpaRepository;

    @Override
    public Optional<EnrollmentEntity> findByUserIdAndCircleId(Long userId, Long circleId) {
        return enrollmentJpaRepository.findByUserIdAndCircleId(userId, circleId);
    }

    @Override
    public EnrollmentEntity save(Enrollment enrollment) {
        return enrollmentJpaRepository.save(EnrollmentEntity.from(enrollment));
    }

    @Override
    public List<EnrollmentEntity> findByCircleIdInAndEnrollmentState(List<Long> circleIds, EnrollmentState enrollmentState) {
        return enrollmentJpaRepository.findByCircleIdInAndEnrollmentState(circleIds, enrollmentState);
    }

    @Override
    public Optional<EnrollmentEntity> findById(Long enrollmentId) {
        return enrollmentJpaRepository.findById(enrollmentId);
    }
}
