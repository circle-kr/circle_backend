package com.circle.circle_backend.circle.infrastructure;

import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.infrastructure.entity.CircleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CircleJpaRepository extends JpaRepository<CircleEntity, Long> {

    Optional<List<CircleEntity>> findByCategory(Category category);
}
