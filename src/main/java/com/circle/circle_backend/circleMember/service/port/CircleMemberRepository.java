package com.circle.circle_backend.circleMember.service.port;


import com.circle.circle_backend.circleMember.domain.CircleMember;

import java.util.List;
import java.util.Optional;

public interface CircleMemberRepository {
    CircleMember save(CircleMember circleMember);

    Optional<CircleMember> findByCircleIdAndUserId(Long circleId, Long userId);

    List<CircleMember> findByUserId(Long userId);

    List<CircleMember> findById(Long circleId);
}
