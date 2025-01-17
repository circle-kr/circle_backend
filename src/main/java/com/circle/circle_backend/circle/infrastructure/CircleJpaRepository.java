package com.circle.circle_backend.circle.infrastructure;

import com.circle.circle_backend.circle.infrastructure.entity.CircleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CircleJpaRepository extends JpaRepository<CircleEntity, Long> {

}
