package com.circle.circle_backend.circle.controller.port;

import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.domain.port.CircleCreateRequest;

public interface CircleService {
    Circle create(CircleCreateRequest circleCreateRequest);

}
