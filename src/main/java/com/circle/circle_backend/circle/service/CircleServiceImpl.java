package com.circle.circle_backend.circle.service;

import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.domain.port.CircleCreateRequest;
import com.circle.circle_backend.circle.controller.port.CircleService;
import com.circle.circle_backend.circle.domain.Circle;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CircleServiceImpl implements CircleService {
    private final CircleRepository circleRepository;

    @Override
    public Circle create(CircleCreateRequest circleCreateRequest) {
        Circle circle = Circle.from(circleCreateRequest);
        circle = circleRepository.save(circle);
        return circle;
    }

    @Override
    public List<Circle> readCircles(Category category) {
        return circleRepository.findByCategory(category).orElse(Collections.emptyList());
    }
}
