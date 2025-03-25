package com.circle.circle_backend.circleMember.controller.port;


import com.circle.circle_backend.circleMember.dto.response.CircleMemberResponse;

import java.util.List;

public interface CircleMemberService {
    List<CircleMemberResponse> read(Long circleId);
}
