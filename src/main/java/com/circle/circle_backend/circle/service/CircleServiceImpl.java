package com.circle.circle_backend.circle.service;

import com.circle.circle_backend.circle.domain.CircleMember;
import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.domain.enums.UserRole;
import com.circle.circle_backend.circle.domain.port.CircleCreateRequest;
import com.circle.circle_backend.circle.controller.port.CircleService;
import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.common.domain.ResourceNotFoundException;
import com.circle.circle_backend.user.domain.User;
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
    private final CircleMemberRepository circleMemberRepository;

    @Override
    public Circle create(CircleCreateRequest circleCreateRequest, User user) {
        Circle circle = circleRepository.save(Circle.from(circleCreateRequest));
        CircleMember circleMember = CircleMember.of(circle, user, UserRole.ADMIN);
        circleMemberRepository.save(circleMember);
        return circle;
    }


    @Override
    public List<Circle> readCircles(Category category) {
        return circleRepository.findByCategory(category).orElse(Collections.emptyList());
    }

    @Override
    public Circle readCircleInfo(Long id) {
        return circleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("circle", id));
    }
}
