package com.circle.circle_backend.circle.service;

import com.circle.circle_backend.circleMember.domain.CircleMember;
import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circleMember.domain.enums.UserRole;
import com.circle.circle_backend.circle.dto.request.CircleCreateRequest;
import com.circle.circle_backend.circle.controller.port.CircleService;
import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.dto.request.CircleUpdateRequest;
import com.circle.circle_backend.circle.infrastructure.entity.CircleEntity;
import com.circle.circle_backend.circleMember.service.CircleMemberRepository;
import com.circle.circle_backend.common.response.responseEnum.ErrorResponseEnum;
import com.circle.circle_backend.exception.impl.ResourceNotFoundException;
import com.circle.circle_backend.exception.impl.AuthException;
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
        return circleRepository.findById(id).map(CircleEntity::toCircle)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorResponseEnum.CIRCLE_NOT_FOUND));
    }

    @Override
    public Circle updateCircleInfo(Long circleId, User user, CircleUpdateRequest circleUpdateRequest) {
        CircleEntity circleEntity = circleRepository.findById(circleId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorResponseEnum.CIRCLE_NOT_FOUND));

        CircleMember circleMember = circleMemberRepository.findByCircleIdAndUserId(circleId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorResponseEnum.CIRCLE_MEMBER_NOT_FOUND)).toCircleMember();

        if (circleMember.getUserRole() != UserRole.ADMIN) {
            throw new AuthException(ErrorResponseEnum.UNAUTHORIZED);
        }

        return circleEntity.updateFrom(circleUpdateRequest).toCircle();
    }



}
