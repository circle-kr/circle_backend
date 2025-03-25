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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EnrollmentCreateController {

    private final EnrollmentService enrollmentService;

    // TODO: 단일책임 가능하도록 프론트와 논의 필요
    @PostMapping("/circles/{circleId}/enrollments")
    public ResponseEntity<CommonResponse<EnrollmentResponse>> createAndUpdate(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                             @PathVariable Long circleId,
                                                                              UriComponentsBuilder uriBuilder) {
        EnrollmentResponse enrollmentResponse =  enrollmentService.createAndUpdate(userDetails.getUser().getId(), circleId);

        URI location = uriBuilder
                .path("/api/circles/{circleId}/enrollments/{enrollmentId}")
                .buildAndExpand(circleId, enrollmentResponse.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(CommonResponse.<EnrollmentResponse>builder()
                        .data(enrollmentResponse)
                        .response(SuccessResponseEnum.CREATE_RESOURCES)
                        .build()
                );
    }

}
