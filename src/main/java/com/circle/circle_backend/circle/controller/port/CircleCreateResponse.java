package com.circle.circle_backend.circle.controller.port;

import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.domain.enums.Characteristic;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class CircleCreateResponse {
    private Long id;
    private String name;
    private String introduce;
    private String notification;
    private Category category;
    private List<Characteristic> characteristics = new ArrayList<>();

    public static CircleCreateResponse from(Circle circle) {
        return CircleCreateResponse.builder()
                .id(circle.getId())
                .name(circle.getName())
                .introduce(circle.getIntroduce())
                .notification(circle.getNotification())
                .category(circle.getCategory())
                .characteristics(circle.getCharacteristics())
                .build();

    }
}
