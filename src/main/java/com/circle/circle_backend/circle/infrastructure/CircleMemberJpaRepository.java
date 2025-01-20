package com.circle.circle_backend.circle.infrastructure;

import com.circle.circle_backend.circle.infrastructure.entity.CircleMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CircleMemberJpaRepository extends JpaRepository<CircleMemberEntity, Long> {
}
