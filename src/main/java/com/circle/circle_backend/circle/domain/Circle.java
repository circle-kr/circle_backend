package com.circle.circle_backend.circle.domain;

import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.domain.enums.Characteristic;
import com.circle.circle_backend.circle.dto.request.CircleUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "circles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Circle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "notification")
    private String notification;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection
    @CollectionTable(name = "circle_characteristics", joinColumns = @JoinColumn(name = "circle_id"))
    @Column(name = "characteristic")
    @Enumerated(EnumType.STRING)
    private List<Characteristic> characteristics = new ArrayList<>();

    @Builder
    public Circle(String name, String introduce, String notification, Category category, List<Characteristic> characteristics) {
        this.name = name;
        this.introduce = introduce;
        this.notification = notification;
        this.category = category;
        this.characteristics = characteristics;
    }

    public Circle updateFrom(CircleUpdateRequest circleUpdateRequest) {
        if (circleUpdateRequest.name != null) this.name = circleUpdateRequest.name;
        if (circleUpdateRequest.introduce != null) this.introduce = circleUpdateRequest.introduce;
        if (circleUpdateRequest.notification != null) this.notification = circleUpdateRequest.notification;
        if (circleUpdateRequest.category != null) this.category = circleUpdateRequest.category;
        if (circleUpdateRequest.characteristics != null) this.characteristics = new ArrayList<>(circleUpdateRequest.characteristics);
        return this;
    }
}
