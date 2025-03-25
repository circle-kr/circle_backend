package com.circle.circle_backend.circle.controller.port;

import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.dto.request.CircleCreateRequest;
import com.circle.circle_backend.circle.dto.request.CircleUpdateRequest;
import com.circle.circle_backend.circle.dto.response.CircleResponse;
import com.circle.circle_backend.user.domain.User;

import java.util.List;

public interface CircleService {
    CircleResponse create(CircleCreateRequest circleCreateRequest, User user);

    List<CircleResponse> readCircles(Category category);

    CircleResponse readCircleInfo(Long circleId);

    CircleResponse updateCircleInfo(Long circleId, User user, CircleUpdateRequest circleUpdateRequest);
}
