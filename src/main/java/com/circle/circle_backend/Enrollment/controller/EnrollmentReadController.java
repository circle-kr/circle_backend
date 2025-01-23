package com.circle.circle_backend.Enrollment.controller;

import com.circle.circle_backend.Enrollment.controller.port.EnrollmentResponse;
import com.circle.circle_backend.Enrollment.domain.Enrollment;
import com.circle.circle_backend.Enrollment.infrastructure.entity.EnrollmentEntity;
import com.circle.circle_backend.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EnrollmentReadController {

    private final EnrollmentService enrollmentService;

    @GetMapping("/circles/{circleId}/enrollments")
    public ResponseEntity<EnrollmentResponse> getEnrollmentState(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                            @PathVariable Long circleId) {
        Optional<EnrollmentEntity> enrollment =  enrollmentService.get(userDetails.getUser(), circleId);
        EnrollmentResponse enrollmentResponse = enrollment
                .map(EnrollmentEntity::toEnrollment)
                .map(EnrollmentResponse::from)
                .orElse(null);

        return ResponseEntity.ok(enrollmentResponse);
    }

}
