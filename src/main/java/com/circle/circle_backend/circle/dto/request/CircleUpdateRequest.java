package com.circle.circle_backend.circle.dto.request;

import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.domain.enums.Characteristic;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CircleUpdateRequest {
    public String name;
    public String introduce;
    public String notification;
    public Category category;
    public List<Characteristic> characteristics;
}
