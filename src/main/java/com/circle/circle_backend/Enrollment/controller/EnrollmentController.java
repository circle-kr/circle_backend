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
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // TODO: 단일책임 가능하도록 프론트와 논의 필요
    @PostMapping("/circles/{circleId}/enrollments")
    public ResponseEntity<EnrollmentResponse> createAndUpdate(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                              @PathVariable Long circleId) {
        Enrollment enrollment =  enrollmentService.createAndUpdate(userDetails.getUser(), circleId);
        return ResponseEntity.ok()
                .body(EnrollmentResponse.from(enrollment));
    }

    @GetMapping("/circles/{circleId}/enrollments")
    public ResponseEntity<EnrollmentResponse> get(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                            @PathVariable Long circleId) {
        Optional<EnrollmentEntity> enrollment =  enrollmentService.get(userDetails.getUser(), circleId);
        EnrollmentResponse enrollmentResponse = enrollment
                .map(EnrollmentEntity::toEnrollment)
                .map(EnrollmentResponse::from)
                .orElse(null);

        return ResponseEntity.ok(enrollmentResponse);
    }

}
