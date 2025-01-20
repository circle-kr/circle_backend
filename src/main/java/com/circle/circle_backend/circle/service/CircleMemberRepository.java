package com.circle.circle_backend.circle.service;

import com.circle.circle_backend.circle.domain.CircleMember;
import com.circle.circle_backend.circle.infrastructure.entity.CircleMemberEntity;

import java.util.Optional;

public interface CircleMemberRepository {
    CircleMember save(CircleMember circleMember);

    Optional<CircleMemberEntity> findByCircleEntity_IdAndUserEntity_Id(Long circleId, Long id);
}
