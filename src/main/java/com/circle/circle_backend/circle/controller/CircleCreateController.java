package com.circle.circle_backend.circle.controller;

import com.circle.circle_backend.circle.dto.response.CircleResponse;
import com.circle.circle_backend.circle.dto.request.CircleCreateRequest;
import com.circle.circle_backend.circle.controller.port.CircleService;
import com.circle.circle_backend.common.response.CommonResponse;
import com.circle.circle_backend.common.response.responseEnum.SuccessResponseEnum;
import com.circle.circle_backend.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/circles")
public class CircleCreateController {

    private final CircleService circleService;

    @PostMapping
    public ResponseEntity<CommonResponse<CircleResponse>> create(@RequestBody CircleCreateRequest circleCreateRequest,
                                                                 @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                 UriComponentsBuilder uriBuilder) {
        CircleResponse circleResponse = circleService.create(circleCreateRequest, userDetails.getUser());

        URI location = uriBuilder
                .path("/api/circles/{id}")
                .buildAndExpand(circleResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(
                CommonResponse.<CircleResponse>builder()
                        .response(SuccessResponseEnum.CREATE_RESOURCES)
                        .data(circleResponse)
                        .build()
        );
    }

}
