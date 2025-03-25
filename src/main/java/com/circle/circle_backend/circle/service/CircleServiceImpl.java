package com.circle.circle_backend.circle.service;

import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.dto.response.CircleResponse;
import com.circle.circle_backend.circle.service.port.CircleRepository;
import com.circle.circle_backend.circleMember.domain.CircleMember;
import com.circle.circle_backend.circleMember.domain.enums.UserRole;
import com.circle.circle_backend.circle.dto.request.CircleCreateRequest;
import com.circle.circle_backend.circle.controller.port.CircleService;
import com.circle.circle_backend.circle.dto.request.CircleUpdateRequest;
import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circleMember.service.port.CircleMemberRepository;
import com.circle.circle_backend.common.response.responseEnum.ErrorResponseEnum;
import com.circle.circle_backend.exception.impl.ResourceException;
import com.circle.circle_backend.exception.impl.AuthException;
import com.circle.circle_backend.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CircleServiceImpl implements CircleService {
    private final CircleRepository circleRepository;
    private final CircleMemberRepository circleMemberRepository;

    @Override
    public CircleResponse create(CircleCreateRequest circleCreateRequest, User user) {

        if (circleRepository.existsByName(circleCreateRequest.getName())) {
            throw new ResourceException(ErrorResponseEnum.DUPLICATED_RESOURCE);
        }

        Circle circle = Circle.builder()
                .name(circleCreateRequest.getName())
                .introduce(circleCreateRequest.getIntroduce())
                .notification(circleCreateRequest.getNotification())
                .category(circleCreateRequest.getCategory())
                .characteristics(circleCreateRequest.getCharacteristics())
                .build();
        circleRepository.save(circle);

        CircleMember circleMember = CircleMember.builder()
                .circle(circle)
                .user(user)
                .userRole(UserRole.ADMIN)
                .build();
        circleMemberRepository.save(circleMember);

        return CircleResponse.from(circle);
    }

    @Override
    public List<CircleResponse> readCircles(Category category) {
        if (!EnumSet.allOf(Category.class).contains(category)) {
            throw new ResourceException(ErrorResponseEnum.BAD_REQUEST_RESOURCE);
        }

        return circleRepository.findByCategory(category).orElse(Collections.emptyList())
                .stream()
                .map(CircleResponse::from)
                .toList();
    }

    @Override
    public CircleResponse readCircleInfo(Long id) {
        Circle circle = circleRepository.findById(id)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));
        return CircleResponse.from(circle);
    }

    @Override
    public CircleResponse updateCircleInfo(Long circleId, User user, CircleUpdateRequest circleUpdateRequest) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        CircleMember circleMember = circleMemberRepository.findByCircleIdAndUserId(circleId, user.getId())
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        if (circleMember.getUserRole() != UserRole.ADMIN) {
            throw new AuthException(ErrorResponseEnum.UNAUTHORIZED);
        }
        circle = circle.updateFrom(circleUpdateRequest);

        return CircleResponse.from(circle);
    }



}
