package com.circle.circle_backend.enrollment.controller;

import com.circle.circle_backend.common.response.CommonResponse;
import com.circle.circle_backend.common.response.responseEnum.SuccessResponseEnum;
import com.circle.circle_backend.enrollment.dto.response.EnrollmentResponse;
import com.circle.circle_backend.enrollment.controller.port.EnrollmentService;
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
    public ResponseEntity<CommonResponse<EnrollmentResponse>> ReadEnrollmentState(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                                @PathVariable Long circleId) {
        Optional<EnrollmentEntity> enrollment =  enrollmentService.readEnrollmentState(userDetails.getUser(), circleId);
        EnrollmentResponse enrollmentResponse = enrollment
                .map(EnrollmentEntity::toEnrollment)
                .map(EnrollmentResponse::from)
                .orElse(null);

        return ResponseEntity.ok()
                .body(CommonResponse.<EnrollmentResponse>builder()
                        .data(enrollmentResponse)
                        .response(SuccessResponseEnum.READ_ENROLLMENT_STATE)
                        .build()
                );
    }

    @GetMapping("/enrollments")
    public ResponseEntity<CommonResponse<List<EnrollmentResponse>>> ReadEnrollmentList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<List<EnrollmentEntity>> enrollments = enrollmentService.readEnrollmentList(userDetails.getUser());

        if (enrollments.isEmpty()) return ResponseEntity.noContent().build();

        List<EnrollmentResponse> enrollmentResponses = enrollments.get().stream()
                .map(EnrollmentEntity::toEnrollment)
                .map(EnrollmentResponse::from)
                .toList();

        return ResponseEntity.ok()
                .body(CommonResponse.<List<EnrollmentResponse>>builder()
                        .data(enrollmentResponses)
                        .response(SuccessResponseEnum.READ_ENROLLMENT_LIST)
                        .build()
                );
    }

}
