package com.circle.circle_backend.Enrollment.controller;

import com.circle.circle_backend.Enrollment.domain.Enrollment;
import com.circle.circle_backend.user.domain.User;

public interface EnrollmentService {
    Enrollment createAndUpdate(User user, Long circleId);
}
