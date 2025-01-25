package com.circle.circle_backend.circleMember.infrastructure;


import com.circle.circle_backend.circleMember.domain.CircleMember;
import com.circle.circle_backend.circleMember.infrastructure.entity.CircleMemberEntity;
import com.circle.circle_backend.circleMember.service.CircleMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CircleMemberRepositoryImpl implements CircleMemberRepository {

    private final CircleMemberJpaRepository circleMemberJpaRepository;

    @Override
    public CircleMember save(CircleMember circleMember) {
        return circleMemberJpaRepository.save(CircleMemberEntity.from(circleMember)).toCircleMember();
    }

    @Override
    public Optional<CircleMemberEntity> findByCircleIdAndUserId(Long circleId, Long id) {
        return circleMemberJpaRepository.findByCircleEntityIdAndUserEntityId(circleId, id);
    }

    @Override
    public List<CircleMemberEntity> findByUserId(Long userId) {
        return circleMemberJpaRepository.findByUserEntityId(userId);
    }

    @Override
    public List<CircleMemberEntity> findById(Long circleId) {
        return circleMemberJpaRepository.findByCircleEntityId(circleId);
    }
}
