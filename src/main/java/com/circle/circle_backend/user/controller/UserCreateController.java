package com.circle.circle_backend.user.controller;

import com.circle.circle_backend.common.response.CommonResponse;
import com.circle.circle_backend.common.response.responseEnum.SuccessResponseEnum;
import com.circle.circle_backend.user.controller.port.UserService;
import com.circle.circle_backend.user.dto.request.UserCreateRequest;
import com.circle.circle_backend.user.dto.request.UserEmailCheckRequest;
import com.circle.circle_backend.user.dto.request.UserNicknameCheckRequest;
import com.circle.circle_backend.user.dto.response.UserEmailCheckResponse;
import com.circle.circle_backend.user.dto.response.UserNicknameCheckResponse;
import com.circle.circle_backend.user.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/signup")
@Builder
public class UserCreateController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CommonResponse<UserResponse>> create(@Valid @RequestBody UserCreateRequest userCreateRequest,
                                                               UriComponentsBuilder uriBuilder) {
        UserResponse userResponse = userService.create(userCreateRequest);
        URI location = uriBuilder
                .path("/api/users/{userId}")
                .buildAndExpand(userResponse.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(CommonResponse.<UserResponse>builder()
                                .data(userResponse)
                                .response(SuccessResponseEnum.CREATE_RESOURCES)
                                .build()
                );
    }

    @GetMapping("/email")
    public ResponseEntity<CommonResponse<UserEmailCheckResponse>> checkEmail(@RequestBody UserEmailCheckRequest userEmailCheckRequest) {
        UserEmailCheckResponse userEmailCheckResponse = userService.checkEmail(userEmailCheckRequest);
        return ResponseEntity.ok()
                .body(CommonResponse.<UserEmailCheckResponse>builder()
                        .data(userEmailCheckResponse)
                        .response(SuccessResponseEnum.CHECK_RESOURCES)
                        .build()
                );
    }

    @GetMapping("/nickname")
    public ResponseEntity<CommonResponse<UserNicknameCheckResponse>> checkNickname(@RequestBody UserNicknameCheckRequest userNicknameCheckRequest) {
        UserNicknameCheckResponse userNicknameCheckResponse = userService.checkNickname(userNicknameCheckRequest);
        return ResponseEntity.ok()
                .body(CommonResponse.<UserNicknameCheckResponse>builder()
                        .data(userNicknameCheckResponse)
                        .response(SuccessResponseEnum.CHECK_RESOURCES)
                        .build()
                );
    }

}
