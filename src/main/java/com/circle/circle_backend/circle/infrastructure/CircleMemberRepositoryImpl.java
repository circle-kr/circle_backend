package com.circle.circle_backend.circle.infrastructure;


import com.circle.circle_backend.circle.domain.CircleMember;
import com.circle.circle_backend.circle.infrastructure.entity.CircleMemberEntity;
import com.circle.circle_backend.circle.service.CircleMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CircleMemberRepositoryImpl implements CircleMemberRepository {

    private final CircleMemberJpaRepository circleMemberJpaRepository;

    @Override
    public CircleMember save(CircleMember circleMember) {
        return circleMemberJpaRepository.save(CircleMemberEntity.from(circleMember)).toCircleMember();
    }
}
