package com.circle.circle_backend.circle.infrastructure;

import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.infrastructure.entity.CircleEntity;
import com.circle.circle_backend.circle.service.CircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CircleRepositoryImpl implements CircleRepository {

    private final CircleJpaRepository circleJpaRepository;

    @Override
    public Circle save(Circle circle) {
        return circleJpaRepository.save(CircleEntity.from(circle)).toCircle();
    }

    @Override
    public Optional<List<Circle>> findByCategory(Category category) {
        return circleJpaRepository.findByCategory(category)
                .map(circleEntities -> circleEntities.stream()
                        .map(CircleEntity::toCircle)
                        .toList());
    }

    @Override
    public Optional<CircleEntity> findById(Long id) {
        return circleJpaRepository.findById(id);
    }

}
