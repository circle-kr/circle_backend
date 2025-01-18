package com.circle.circle_backend.circle.controller;

import com.circle.circle_backend.circle.controller.port.CircleResponse;
import com.circle.circle_backend.circle.controller.port.CircleService;
import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.domain.enums.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/circles")
public class CircleReadController {

    public final CircleService circleService;

    @GetMapping
    public ResponseEntity<List<CircleResponse>> readCircles(@RequestParam(defaultValue = "LANGUAGE")Category category) {
        List<Circle> circles = circleService.readCircles(category);
        List<CircleResponse> circleReadResponses = circles.stream()
                .map(CircleResponse::from)
                .toList();
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(circleReadResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CircleResponse> readCircleInfo(@PathVariable Long id) {
        Circle circle = circleService.readCircleInfo(id);
        CircleResponse circleReadResponses = CircleResponse.from(circle);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(circleReadResponses);
    }
}
