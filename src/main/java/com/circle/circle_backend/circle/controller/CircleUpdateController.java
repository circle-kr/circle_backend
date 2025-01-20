package com.circle.circle_backend.circle.controller;

import com.circle.circle_backend.circle.controller.port.CircleResponse;
import com.circle.circle_backend.circle.controller.port.CircleService;
import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.domain.port.CircleUpdateRequest;
import com.circle.circle_backend.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/circles")
public class CircleUpdateController {

    public final CircleService circleService;

    @PatchMapping("/{circleId}")
    public ResponseEntity<CircleResponse> updateCircleInfo(@PathVariable Long circleId,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                           @RequestBody CircleUpdateRequest circleUpdateRequest) {
        Circle circle = circleService.updateCircleInfo(circleId, userDetails.getUser(), circleUpdateRequest);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(CircleResponse.from(circle));
    }
}
