package com.circle.circle_backend.user.service;

import com.circle.circle_backend.common.TestContainer;
import com.circle.circle_backend.user.controller.port.UserService;
import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.dto.request.UserCreateRequest;
import com.circle.circle_backend.user.mock.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceImplTest {

    private UserService userService;

    @BeforeEach
    void init() {
        this.userService = UserServiceImpl.builder()
                .userRepository(new FakeUserRepository())
                .build();
    }

    @Test
    void UserCreateDto로_유저를_생성할_수_있다() {
        // given
        TestContainer testContainer = new TestContainer();
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .email("test@email.com")
                .password("password")
                .firstName("Test")
                .lastName("Test")
                .nickname("Test")
                .build();

        // when
        User user = testContainer.userService.create(userCreateRequest);

        // then
        assertThat(user.getEmail()).isEqualTo("test@email.com");
        assertThat(user.getPassword()).isEqualTo("encodedpassword");
        assertThat(user.getFirstName()).isEqualTo("Test");
        assertThat(user.getLastName()).isEqualTo("Test");
        assertThat(user.getNickname()).isEqualTo("Test");
    }

}