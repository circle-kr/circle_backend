package com.circle.circle_backend.circleMember.controller;

import com.circle.circle_backend.circleMember.controller.dto.response.CircleMemberResponse;
import com.circle.circle_backend.circleMember.controller.port.CircleMemberService;
import com.circle.circle_backend.circleMember.domain.CircleMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CircleMemberReadController {

    private final CircleMemberService circleMemberService;

    @GetMapping("/circles/{circleId}/members")
    public ResponseEntity<List<CircleMemberResponse>> read(@PathVariable Long circleId) {
        List<CircleMember> circleMember = circleMemberService.read(circleId);

        List<CircleMemberResponse> circleMemberResponses = circleMember.stream()
                .map(CircleMemberResponse::from)
                .toList();

        return ResponseEntity
                .ok(circleMemberResponses);
    }
}
