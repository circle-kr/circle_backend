package com.circle.circle_backend.enrollment.service;

import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.common.response.responseEnum.ErrorResponseEnum;
import com.circle.circle_backend.enrollment.controller.port.EnrollmentService;
import com.circle.circle_backend.enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.enrollment.dto.response.EnrollmentResponse;
import com.circle.circle_backend.enrollment.domain.Enrollment;
import com.circle.circle_backend.circleMember.domain.enums.UserRole;
import com.circle.circle_backend.circleMember.domain.CircleMember;
import com.circle.circle_backend.circleMember.service.port.CircleMemberRepository;
import com.circle.circle_backend.circle.service.port.CircleRepository;
import com.circle.circle_backend.exception.impl.ResourceException;
import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.service.port.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CircleRepository circleRepository;
    private final CircleMemberRepository circleMemberRepository;

    @Override
    public EnrollmentResponse createAndUpdate(Long userId, Long circleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        // TODO: admin인지 확인
        return enrollmentRepository.findByUserIdAndCircleId(user.getId(), circleId)
                .map(enrollment -> {
                    if (enrollment.canUpdateState()) return EnrollmentResponse.from(enrollment.updateEnrollmentState());
                    return EnrollmentResponse.from(enrollment);})
                .orElseGet(() -> {
                    Enrollment newEnrollment = Enrollment.builder()
                            .user(user)
                            .circle(circle)
                            .enrollmentState(EnrollmentState.PENDING)
                            .build();
                    return EnrollmentResponse.from(enrollmentRepository.save(newEnrollment));
                });
    }

    @Override
    public EnrollmentResponse readEnrollmentState(User user, Long circleId) {
        if (!circleRepository.existsById(circleId)) {
            throw new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND);
        }

        Enrollment enrollment = enrollmentRepository.findByUserIdAndCircleId(user.getId(), circleId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        return EnrollmentResponse.from(enrollment);
    }

    @Override
    public List<EnrollmentResponse> readEnrollmentList(User user) {
        List<CircleMember> circleMembers = circleMemberRepository.findByUserId(user.getId());

        List<Long> circleIds = circleMembers.stream()
                .filter(CircleMember::isAdmin)
                .map(circleMember -> circleMember.getCircle().getId())
                .toList();

        return enrollmentRepository.findPendingByCircleIds(circleIds)
                .stream()
                .map(EnrollmentResponse::from)
                .toList();
    }

    @Override
    public EnrollmentResponse acceptOrDecline(User user, Long enrollmentId, EnrollmentState enrollmentState) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        enrollment.updateEnrollmentStateByAdmin(enrollmentState);

        if (enrollmentState == EnrollmentState.ACCEPTED) {
            circleMemberRepository.save(CircleMember.builder()
                    .circle(enrollment.getCircle())
                    .user(user)
                    .userRole(UserRole.MEMBER)
                    .build());
        }

        return EnrollmentResponse.from(enrollment);
    }



}
