package com.circle.circle_backend.circle.controller.port;

import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.domain.port.CircleCreateRequest;

import java.util.List;

public interface CircleService {
    Circle create(CircleCreateRequest circleCreateRequest);

    List<Circle> readCircles(Category category);

    Circle readCircleInfo(Long id);


}
