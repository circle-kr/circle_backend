package com.circle.circle_backend.circle.service;


import com.circle.circle_backend.circle.domain.Circle;

public interface CircleRepository {

    Circle save(Circle circle);
}
