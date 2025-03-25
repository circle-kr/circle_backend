package com.circle.circle_backend.circleMember.infrastructure;


import com.circle.circle_backend.circleMember.domain.CircleMember;
import com.circle.circle_backend.circleMember.service.port.CircleMemberRepository;
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
        return circleMemberJpaRepository.save(circleMember);
    }

    @Override
    public Optional<CircleMember> findByCircleIdAndUserId(Long circleId, Long id) {
        return circleMemberJpaRepository.findByCircleIdAndUserId(circleId, id);
    }

    @Override
    public List<CircleMember> findByUserId(Long userId) {
        return circleMemberJpaRepository.findByUserId(userId);
    }

    @Override
    public List<CircleMember> findById(Long circleId) {
        return circleMemberJpaRepository.findByCircleId(circleId);
    }
}
