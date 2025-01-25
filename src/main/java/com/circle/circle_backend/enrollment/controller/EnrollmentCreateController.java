package com.circle.circle_backend.enrollment.controller;

import com.circle.circle_backend.enrollment.controller.port.EnrollmentResponse;
import com.circle.circle_backend.enrollment.domain.Enrollment;
import com.circle.circle_backend.enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EnrollmentCreateController {

    private final EnrollmentService enrollmentService;

    // TODO: 단일책임 가능하도록 프론트와 논의 필요
    @PostMapping("/circles/{circleId}/enrollments")
    public ResponseEntity<EnrollmentResponse> createAndUpdate(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                              @PathVariable Long circleId) {
        Enrollment enrollment =  enrollmentService.createAndUpdate(userDetails.getUser(), circleId);
        return ResponseEntity.ok()
                .body(EnrollmentResponse.from(enrollment));
    }

    @PostMapping("/enrollments/{enrollmentId}")
    public ResponseEntity<EnrollmentResponse> acceptOrDecline(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                              @PathVariable Long enrollmentId,
                                                              @RequestParam EnrollmentState enrollmentState) {
        Enrollment enrollment =  enrollmentService.acceptOrDecline(userDetails.getUser(), enrollmentId, enrollmentState);
        return ResponseEntity.ok()
                .body(EnrollmentResponse.from(enrollment));
    }


}
