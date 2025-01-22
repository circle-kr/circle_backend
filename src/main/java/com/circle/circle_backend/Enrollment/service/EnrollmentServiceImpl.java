package com.circle.circle_backend.Enrollment.service;

import com.circle.circle_backend.Enrollment.controller.EnrollmentService;
import com.circle.circle_backend.Enrollment.domain.Enrollment;
import com.circle.circle_backend.Enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.Enrollment.infrastructure.entity.EnrollmentEntity;
import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.service.CircleRepository;
import com.circle.circle_backend.common.domain.ResourceNotFoundException;
import com.circle.circle_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CircleRepository circleRepository;

    @Override
    public Enrollment createAndUpdate(User user, Long circleId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new ResourceNotFoundException("circle", circleId))
                .toCircle();
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findByUserIdAndCircleId(user.getId(), circleId);

        if (enrollmentEntity == null) {
            Enrollment enrollment = Enrollment.builder()
                    .user(user)
                    .circle(circle)
                    .enrollmentState(EnrollmentState.PENDING)
                    .build();
            enrollmentEntity = enrollmentRepository.save(enrollment);
        } else {
            if (enrollmentEntity.canUpdateState()) {
                enrollmentEntity = enrollmentEntity.updateEnrollmentState();
            }
        }
        return enrollmentEntity.toEnrollment();
    }
}
