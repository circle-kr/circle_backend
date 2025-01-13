package com.circle.circle_backend.user.service;

import com.circle.circle_backend.security.service.PasswordUtils;
import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.domain.UserCreateRequest;
import com.circle.circle_backend.user.mock.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void init() {
        this.userService = UserService.builder()
                .userRepository(new FakeUserRepository())
                .build();
    }

    @Test
    void UserCreateDto로_유저를_생성할_수_있다() {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .email("test@email.com")
                .password("password")
                .firstName("Test")
                .lastName("Test")
                .nickname("Test")
                .build();

        // when
        User user = userService.create(userCreateRequest);

        // then
        assertThat(user.getEmail()).isEqualTo("test@email.com");
        assertThat(user.getPassword()).isEqualTo("password");
        assertThat(user.getFirstName()).isEqualTo("Test");
        assertThat(user.getLastName()).isEqualTo("Test");
        assertThat(user.getNickname()).isEqualTo("Test");
    }

}