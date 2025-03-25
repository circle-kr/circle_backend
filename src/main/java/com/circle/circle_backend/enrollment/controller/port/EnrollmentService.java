package com.circle.circle_backend.enrollment.controller.port;

import com.circle.circle_backend.enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.enrollment.dto.response.EnrollmentResponse;
import com.circle.circle_backend.user.domain.User;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse createAndUpdate(Long userId, Long circleId);

    EnrollmentResponse readEnrollmentState(User user, Long circleId);

    List<EnrollmentResponse> readEnrollmentList(User user);

    EnrollmentResponse acceptOrDecline(User user, Long enrollmentId, EnrollmentState enrollmentState);
}
