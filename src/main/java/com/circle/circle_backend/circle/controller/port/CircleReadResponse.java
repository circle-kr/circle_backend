package com.circle.circle_backend.circle.controller.port;

import com.circle.circle_backend.circle.domain.Circle;
import lombok.Builder;

@Builder
public class CircleReadResponse {
    private Long id;
    private String name;
    private String introduce;

    public static CircleReadResponse from(Circle circle) {
        return CircleReadResponse.builder()
                .id(circle.getId())
                .name(circle.getName())
                .introduce(circle.getName())
                .build();
    }
}
