package com.circle.circle_backend.user.controller;

import com.circle.circle_backend.security.service.UserDetailsImpl;
import com.circle.circle_backend.user.domain.User;
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
    public ResponseEntity<UserResponse> readMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userService.readMyInfo(userDetails.getUser());

        return ResponseEntity.ok(UserResponse.from(user));
    }
}
