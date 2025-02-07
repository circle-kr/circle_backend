package com.circle.circle_backend.user.dto.request;

import com.circle.circle_backend.common.constant.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCreateRequest {
    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    private String firstName;

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    private String lastName;

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    private String nickname;

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = ValidationMessage.RESPONSE_NOT_MATCH)
    private String email;

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    @Pattern(regexp = "^(?!.*\\s)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=<>?/]).{8,15}$",
            message = ValidationMessage.RESPONSE_NOT_MATCH)
    private String password;

}
