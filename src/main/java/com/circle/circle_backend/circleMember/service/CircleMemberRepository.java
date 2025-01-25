package com.circle.circle_backend.circleMember.service;


import com.circle.circle_backend.circleMember.domain.CircleMember;
import com.circle.circle_backend.circleMember.infrastructure.entity.CircleMemberEntity;

import java.util.List;
import java.util.Optional;

public interface CircleMemberRepository {
    CircleMember save(CircleMember circleMember);

    Optional<CircleMemberEntity> findByCircleIdAndUserId(Long circleId, Long userId);

    List<CircleMemberEntity> findByUserId(Long userId);
}
