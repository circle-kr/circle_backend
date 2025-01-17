package com.circle.circle_backend.circle.infrastructure.entity;

import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.domain.enums.Characteristic;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "circles")
public class CircleEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "notification")
    private String notification;

    @Column(name = "category")
    private Category category;

    @ElementCollection
    @CollectionTable(name = "circle_characteristics", joinColumns = @JoinColumn(name = "circle_id"))
    @Column(name = "characteristic")
    private List<Characteristic> characteristics = new ArrayList<>();

    public static CircleEntity from(Circle circle) {
        CircleEntity circleEntity = new CircleEntity();
        circleEntity.name = circle.getName();
        circleEntity.introduce = circle.getIntroduce();
        circleEntity.notification = circle.getNotification();
        circleEntity.category = circle.getCategory();
        circleEntity.characteristics = circle.getCharacteristics();
        return circleEntity;
    }

    public Circle toCircle() {
        return Circle.builder()
                .id(this.id)
                .name(this.name)
                .introduce(this.introduce)
                .notification(this.notification)
                .category(this.category)
                .characteristics(this.characteristics)
                .build();
    }
}
