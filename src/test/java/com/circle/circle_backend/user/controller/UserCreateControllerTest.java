package com.circle.circle_backend.user.controller;

import com.circle.circle_backend.common.TestContainer;
import com.circle.circle_backend.user.domain.UserCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class UserCreateControllerTest {

    @Test
    void 사용자는_회원가입을_할_수_있다() {
        // given
        TestContainer testContainer = new TestContainer();
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .email("test@email.com")
                .password("password")
                .firstName("Test")
                .lastName("Test")
                .nickname("Test")
                .build();

        // when
        ResponseEntity<UserResponse> response= testContainer.userCreateController.create(userCreateDto);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(response.getBody().getEmail()).isEqualTo(userCreateDto.getEmail());
        assertThat(response.getBody().getNickname()).isEqualTo(userCreateDto.getNickname());

    }

}