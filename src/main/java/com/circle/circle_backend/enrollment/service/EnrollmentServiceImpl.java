package com.circle.circle_backend.enrollment.service;

import com.circle.circle_backend.common.response.responseEnum.ErrorResponseEnum;
import com.circle.circle_backend.enrollment.controller.port.EnrollmentService;
import com.circle.circle_backend.enrollment.domain.Enrollment;
import com.circle.circle_backend.enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.enrollment.infrastructure.entity.EnrollmentEntity;
import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circleMember.domain.CircleMember;
import com.circle.circle_backend.circleMember.domain.enums.UserRole;
import com.circle.circle_backend.circleMember.infrastructure.entity.CircleMemberEntity;
import com.circle.circle_backend.circleMember.service.CircleMemberRepository;
import com.circle.circle_backend.circle.service.CircleRepository;
import com.circle.circle_backend.exception.impl.ResourceException;
import com.circle.circle_backend.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CircleRepository circleRepository;
    private final CircleMemberRepository circleMemberRepository;

    @Override
    public Enrollment createAndUpdate(User user, Long circleId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND))
                .toCircle();

        Optional<EnrollmentEntity> optionalEnrollmentEntity =
                enrollmentRepository.findByUserIdAndCircleId(user.getId(), circleId);

        EnrollmentEntity enrollmentEntity;

        if (optionalEnrollmentEntity.isEmpty()) {
            Enrollment newEntity = Enrollment.builder()
                    .user(user)
                    .circle(circle)
                    .enrollmentState(EnrollmentState.PENDING)
                    .build();
            enrollmentEntity = enrollmentRepository.save(newEntity);
        } else {
            enrollmentEntity = optionalEnrollmentEntity.get();
            if (enrollmentEntity.canUpdateState()) {
                enrollmentEntity = enrollmentEntity.updateEnrollmentState(enrollmentEntity.getEnrollmentState());
            }
        }
        return enrollmentEntity.toEnrollment();
    }


    @Override
    public Optional<EnrollmentEntity> readEnrollmentState(User user, Long circleId) {
        circleRepository.findById(circleId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND))
                .toCircle();

        return enrollmentRepository.findByUserIdAndCircleId(user.getId(), circleId);
    }

    @Override
    public Optional<List<EnrollmentEntity>> readEnrollmentList(User user) {
        List<CircleMemberEntity> circleMembers = circleMemberRepository.findByUserId(user.getId());
        if (circleMembers.isEmpty()) return Optional.empty();

        List<Long> circleIds = circleMembers.stream()
                .map(CircleMemberEntity::toCircleMember)
                .filter(CircleMember::isAdmin)
                .map(circleMember -> circleMember.getCircle().getId())
                .toList();

        if (circleIds.isEmpty()) return Optional.empty();

        return Optional.ofNullable(enrollmentRepository.findByCircleIdInAndEnrollmentState(circleIds, EnrollmentState.PENDING));
    }

    @Override
    public Enrollment acceptOrDecline(User user, Long enrollmentId, EnrollmentState enrollmentState) {
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        enrollmentEntity = enrollmentEntity.updateEnrollmentState(enrollmentState);

        if (enrollmentState == EnrollmentState.ACCEPTED) {
            Circle circle = enrollmentEntity.getCircle().toCircle();

            CircleMember circleMember = CircleMember.builder()
                    .circle(circle)
                    .user(user)
                    .userRole(UserRole.MEMBER)
                    .build();

            circleMemberRepository.save(circleMember);
        }

        return enrollmentEntity.toEnrollment();
    }



}
