package com.circle.circle_backend.circleMember.controller.port;


import com.circle.circle_backend.circleMember.domain.CircleMember;

import java.util.List;

public interface CircleMemberService {
    List<CircleMember> read(Long circleId);
}
