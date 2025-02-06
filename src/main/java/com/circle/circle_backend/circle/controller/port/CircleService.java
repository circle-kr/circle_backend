package com.circle.circle_backend.circle.controller.port;

import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.dto.request.CircleCreateRequest;
import com.circle.circle_backend.circle.dto.request.CircleUpdateRequest;
import com.circle.circle_backend.user.domain.User;

import java.util.List;

public interface CircleService {
    Circle create(CircleCreateRequest circleCreateRequest, User user);

    List<Circle> readCircles(Category category);

    Circle readCircleInfo(Long circleId);

    Circle updateCircleInfo(Long circleId, User user, CircleUpdateRequest circleUpdateRequest);
}
