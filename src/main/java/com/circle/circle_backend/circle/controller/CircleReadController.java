package com.circle.circle_backend.circle.controller;

import com.circle.circle_backend.circle.dto.response.CircleResponse;
import com.circle.circle_backend.circle.controller.port.CircleService;
import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.common.response.CommonResponse;
import com.circle.circle_backend.common.response.responseEnum.SuccessResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/circles")
public class CircleReadController {

    public final CircleService circleService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<CircleResponse>>> readCircles(@RequestParam(defaultValue = "LANGUAGE")Category category) {
        List<CircleResponse> circleReadResponses = circleService.readCircles(category);

        return ResponseEntity.ok()
                .body(CommonResponse.<List<CircleResponse>>builder()
                        .response(SuccessResponseEnum.READ_RESOURCES)
                        .data(circleReadResponses)
                        .build());
    }

    @GetMapping("/{circleId}")
    public ResponseEntity<CommonResponse<CircleResponse>> readCircleInfo(@PathVariable Long circleId) {
        CircleResponse circleResponse = circleService.readCircleInfo(circleId);
        return ResponseEntity.ok()
                .body(CommonResponse.<CircleResponse>builder()
                        .response(SuccessResponseEnum.READ_RESOURCES)
                        .data(circleResponse)
                        .build());
    }
}
