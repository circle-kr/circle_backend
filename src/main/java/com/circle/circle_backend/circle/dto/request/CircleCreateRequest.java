package com.circle.circle_backend.circle.dto.request;

import com.circle.circle_backend.circle.domain.enums.Category;
import com.circle.circle_backend.circle.domain.enums.Characteristic;
import com.circle.circle_backend.common.constant.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CircleCreateRequest {

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    public String name;

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    public String introduce;

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    public String notification;

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    public Category category;

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    public List<Characteristic> characteristics;

}
