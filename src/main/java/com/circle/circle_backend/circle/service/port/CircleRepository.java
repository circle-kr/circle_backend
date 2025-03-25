package com.circle.circle_backend.circle.service.port;


import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.domain.Circle;

import java.util.List;
import java.util.Optional;

public interface CircleRepository {

    Circle save(Circle circle);

    Optional<List<Circle>> findByCategory(Category category);

    Optional<Circle> findById(Long id);

    boolean existsByName(String name);

    boolean existsById(Long circleId);
}
