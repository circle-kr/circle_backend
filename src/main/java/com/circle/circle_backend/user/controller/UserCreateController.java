package com.circle.circle_backend.user.controller;

import com.circle.circle_backend.common.response.CommonResponse;
import com.circle.circle_backend.common.response.responseEnum.SuccessResponseEnum;
import com.circle.circle_backend.user.controller.port.UserService;
import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.dto.request.UserCreateRequest;
import com.circle.circle_backend.user.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Builder
public class UserCreateController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CommonResponse<UserResponse>> create(@Valid @RequestBody UserCreateRequest userCreateRequest,
                                                               UriComponentsBuilder uriBuilder) {
        User user = userService.create(userCreateRequest);
        URI location = uriBuilder
                .path("/api/users/{userId}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(CommonResponse.<UserResponse>builder()
                                .data(UserResponse.from(user))
                                .response(SuccessResponseEnum.CREATE_USER)
                                .build()
                );
    }
}
