package com.circle.circle_backend.circle.infrastructure;

import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.service.port.CircleRepository;
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
        return circleJpaRepository.save(circle);
    }

    @Override
    public Optional<List<Circle>> findByCategory(Category category) {
        return circleJpaRepository.findByCategory(category);
    }

    @Override
    public Optional<Circle> findById(Long id) {
        return circleJpaRepository.findById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return circleJpaRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long circleId) {
        return circleJpaRepository.existsById(circleId);
    }

}
