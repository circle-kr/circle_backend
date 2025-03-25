package com.circle.circle_backend.user.controller;

import com.circle.circle_backend.common.response.CommonResponse;
import com.circle.circle_backend.common.response.responseEnum.SuccessResponseEnum;
import com.circle.circle_backend.security.service.UserDetailsImpl;
import com.circle.circle_backend.user.controller.port.UserService;
import com.circle.circle_backend.user.dto.response.MyInfoResponse;
import com.circle.circle_backend.user.dto.response.UserInfoResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Builder
public class UserReadController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<CommonResponse<MyInfoResponse>> readMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        MyInfoResponse myInfoResponse = userService.readMyInfo(userDetails.getUser().getId());

        return ResponseEntity.ok()
                .body(CommonResponse.<MyInfoResponse>builder()
                        .data(myInfoResponse)
                        .response(SuccessResponseEnum.READ_RESOURCES)
                        .build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CommonResponse<UserInfoResponse>> readUserInfo(@PathVariable Long userId) {
        UserInfoResponse userInfoResponse = userService.readUserInfo(userId);

        return ResponseEntity.ok()
                .body(CommonResponse.<UserInfoResponse>builder()
                        .data(userInfoResponse)
                        .response(SuccessResponseEnum.READ_RESOURCES)
                        .build());
    }
}
