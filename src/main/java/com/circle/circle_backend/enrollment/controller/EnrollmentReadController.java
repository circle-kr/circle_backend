package com.circle.circle_backend.enrollment.controller;

import com.circle.circle_backend.enrollment.controller.port.EnrollmentResponse;
import com.circle.circle_backend.enrollment.infrastructure.entity.EnrollmentEntity;
import com.circle.circle_backend.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EnrollmentReadController {

    private final EnrollmentService enrollmentService;

    @GetMapping("/circles/{circleId}/enrollments")
    public ResponseEntity<EnrollmentResponse> getEnrollmentState(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                            @PathVariable Long circleId) {
        Optional<EnrollmentEntity> enrollment =  enrollmentService.getEnrollmentState(userDetails.getUser(), circleId);
        EnrollmentResponse enrollmentResponse = enrollment
                .map(EnrollmentEntity::toEnrollment)
                .map(EnrollmentResponse::from)
                .orElse(null);

        return ResponseEntity.ok(enrollmentResponse);
    }

    @GetMapping("/enrollments")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollments(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<List<EnrollmentEntity>> enrollments = enrollmentService.getEnrollments(userDetails.getUser());

        if (enrollments.isEmpty()) return ResponseEntity.noContent().build();

        List<EnrollmentResponse> enrollmentResponses = enrollments.get().stream()
                .map(EnrollmentEntity::toEnrollment)
                .map(EnrollmentResponse::from)
                .toList();

        return ResponseEntity.ok(enrollmentResponses); // 200 OK 반환
    }

}
