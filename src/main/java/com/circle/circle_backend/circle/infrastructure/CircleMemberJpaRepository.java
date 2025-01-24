package com.circle.circle_backend.circle.infrastructure;

import com.circle.circle_backend.circle.infrastructure.entity.CircleMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CircleMemberJpaRepository extends JpaRepository<CircleMemberEntity, Long> {
    Optional<CircleMemberEntity> findByCircleEntityIdAndUserEntityId(Long circleId, Long userId);

    List<CircleMemberEntity> findByUserEntityId(Long userId);
}
