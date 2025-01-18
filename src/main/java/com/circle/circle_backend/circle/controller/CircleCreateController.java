package com.circle.circle_backend.circle.controller;

import com.circle.circle_backend.circle.controller.port.CircleResponse;
import com.circle.circle_backend.circle.domain.port.CircleCreateRequest;
import com.circle.circle_backend.circle.controller.port.CircleService;
import com.circle.circle_backend.circle.domain.Circle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/circles")
public class CircleCreateController {

    private final CircleService circleService;

    @PostMapping
    public ResponseEntity<CircleResponse> create(@RequestBody CircleCreateRequest circleCreateRequest) {
        Circle circle = circleService.create(circleCreateRequest);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(CircleResponse.from(circle));
    }
}
