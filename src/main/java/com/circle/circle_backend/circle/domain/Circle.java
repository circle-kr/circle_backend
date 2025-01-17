package com.circle.circle_backend.circle.domain;

import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.domain.enums.Characteristic;
import com.circle.circle_backend.circle.domain.port.CircleCreateRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Circle {

    private Long id;

    private String name;

    private String introduce;

    private String notification;

    private Category category;

    private List<Characteristic> characteristics = new ArrayList<>();

    @Builder
    public Circle(Long id, String name, String introduce, String notification, Category category, List<Characteristic> characteristics) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.notification = notification;
        this.category = category;
        this.characteristics = characteristics;
    }

    public static Circle from(CircleCreateRequest circleCreateRequest) {
        return Circle.builder()
                .name(circleCreateRequest.getName())
                .introduce(circleCreateRequest.getIntroduce())
                .notification(circleCreateRequest.getNotification())
                .category(circleCreateRequest.getCategory())
                .characteristics(circleCreateRequest.getCharacteristics())
                .build();
    }


}
