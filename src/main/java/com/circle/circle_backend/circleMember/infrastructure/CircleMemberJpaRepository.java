package com.circle.circle_backend.circleMember.infrastructure;

import com.circle.circle_backend.circleMember.domain.CircleMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CircleMemberJpaRepository extends JpaRepository<CircleMember, Long> {
    Optional<CircleMember> findByCircleIdAndUserId(Long circleId, Long userId);

    List<CircleMember> findByUserId(Long userId);

    List<CircleMember> findByCircleId(Long circleId);
}
