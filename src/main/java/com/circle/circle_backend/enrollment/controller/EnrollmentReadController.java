package com.circle.circle_backend.enrollment.controller;

import com.circle.circle_backend.common.response.CommonResponse;
import com.circle.circle_backend.common.response.responseEnum.SuccessResponseEnum;
import com.circle.circle_backend.enrollment.dto.response.EnrollmentResponse;
import com.circle.circle_backend.enrollment.controller.port.EnrollmentService;
import com.circle.circle_backend.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EnrollmentReadController {

    private final EnrollmentService enrollmentService;

    @GetMapping("/circles/{circleId}/enrollments")
    public ResponseEntity<CommonResponse<EnrollmentResponse>> ReadEnrollmentState(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                                @PathVariable Long circleId) {
        EnrollmentResponse enrollmentResponse =  enrollmentService.readEnrollmentState(userDetails.getUser(), circleId);

        return ResponseEntity.ok()
                .body(CommonResponse.<EnrollmentResponse>builder()
                        .data(enrollmentResponse)
                        .response(SuccessResponseEnum.READ_RESOURCES)
                        .build()
                );
    }

    @GetMapping("/enrollments")
    public ResponseEntity<CommonResponse<List<EnrollmentResponse>>> ReadEnrollmentList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<EnrollmentResponse> enrollmentResponses = enrollmentService.readEnrollmentList(userDetails.getUser());

        return ResponseEntity.ok()
                .body(CommonResponse.<List<EnrollmentResponse>>builder()
                        .data(enrollmentResponses)
                        .response(SuccessResponseEnum.READ_RESOURCES)
                        .build()
                );

    }

}
