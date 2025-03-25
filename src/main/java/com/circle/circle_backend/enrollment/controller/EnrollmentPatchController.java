package com.circle.circle_backend.enrollment.controller;

import com.circle.circle_backend.common.response.CommonResponse;
import com.circle.circle_backend.common.response.responseEnum.SuccessResponseEnum;
import com.circle.circle_backend.enrollment.controller.port.EnrollmentService;
import com.circle.circle_backend.enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.enrollment.dto.response.EnrollmentResponse;
import com.circle.circle_backend.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EnrollmentPatchController {

    private final EnrollmentService enrollmentService;

    @PatchMapping("/enrollments/{enrollmentId}")
    public ResponseEntity<CommonResponse<EnrollmentResponse>> acceptOrDecline(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                              @PathVariable Long enrollmentId,
                                                              @RequestParam EnrollmentState enrollmentState) {
        EnrollmentResponse enrollmentResponse =  enrollmentService.acceptOrDecline(userDetails.getUser(), enrollmentId, enrollmentState);

        return ResponseEntity.ok()
                .body(CommonResponse.<EnrollmentResponse>builder()
                        .data(enrollmentResponse)
                        .response(SuccessResponseEnum.UPDATE_RESOURCES)
                        .build()
                );
    }


}
