package com.circle.circle_backend.user.controller;

import com.circle.circle_backend.common.response.CommonResponse;
import com.circle.circle_backend.common.response.responseEnum.SuccessResponseEnum;
import com.circle.circle_backend.security.service.UserDetailsImpl;
import com.circle.circle_backend.user.controller.port.UserService;
import com.circle.circle_backend.user.dto.request.UserPatchRequest;
import com.circle.circle_backend.user.dto.response.MyInfoResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Builder
public class UserPatchController {

    private final UserService userService;

    @PatchMapping
    public ResponseEntity<CommonResponse<MyInfoResponse>> patch(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                               @RequestBody UserPatchRequest userPatchRequest) {
        MyInfoResponse myInfoResponse = userService.patch(userDetails.getUser().getId(), userPatchRequest);
        return ResponseEntity.ok()
                .body(CommonResponse.<MyInfoResponse>builder()
                        .data(myInfoResponse)
                        .response(SuccessResponseEnum.UPDATE_RESOURCES)
                        .build());
    }
}
