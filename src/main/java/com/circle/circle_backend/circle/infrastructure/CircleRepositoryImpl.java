package com.circle.circle_backend.circle.infrastructure;

import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.infrastructure.entity.CircleEntity;
import com.circle.circle_backend.circle.service.CircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CircleRepositoryImpl implements CircleRepository {

    private final CircleJpaRepository circleJpaRepository;

    @Override
    public Circle save(Circle circle) {
        return circleJpaRepository.save(CircleEntity.from(circle)).toCircle();
    }
}
