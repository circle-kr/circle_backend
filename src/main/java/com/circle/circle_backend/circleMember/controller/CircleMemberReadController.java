package com.circle.circle_backend.circleMember.controller;

import com.circle.circle_backend.circleMember.dto.response.CircleMemberResponse;
import com.circle.circle_backend.circleMember.controller.port.CircleMemberService;
import com.circle.circle_backend.common.response.CommonResponse;
import com.circle.circle_backend.common.response.responseEnum.SuccessResponseEnum;
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
    public ResponseEntity<CommonResponse<List<CircleMemberResponse>>> read(@PathVariable Long circleId) {
        List<CircleMemberResponse> circleMemberResponses = circleMemberService.read(circleId);

        return ResponseEntity.ok()
                .body(CommonResponse.<List<CircleMemberResponse>>builder()
                        .response(SuccessResponseEnum.READ_RESOURCES)
                        .data(circleMemberResponses)
                        .build());
    }
}
