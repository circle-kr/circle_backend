package com.circle.circle_backend.circle.dto.response;

import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.domain.enums.Characteristic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CircleResponse {
    private Long id;
    private String name;
    private String introduce;
    private String notification;
    private Category category;
    private List<Characteristic> characteristics;

    public static CircleResponse from(Circle circle) {
        return CircleResponse.builder()
                .id(circle.getId())
                .name(circle.getName())
                .introduce(circle.getIntroduce())
                .notification(circle.getNotification())
                .category(circle.getCategory())
                .characteristics(circle.getCharacteristics())
                .build();
    }
}
