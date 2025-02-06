package com.circle.circle_backend.user.domain;

import com.circle.circle_backend.user.dto.request.UserCreateRequest;
import com.circle.circle_backend.user.mock.FakePasswordUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    private FakePasswordUtils fakePasswordUtils = new FakePasswordUtils();

    @Test
    public void User를_UserCreateDto로_생성할_수_있다() {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .email("test@test.com")
                .firstName("test")
                .lastName("test")
                .nickname("test")
                .password("password")
                .build();

        // when
        String encodedPassword = fakePasswordUtils.encode(userCreateRequest.getPassword());
        User user = User.from(userCreateRequest, encodedPassword);

        // then
        assertThat(user.getEmail()).isEqualTo("test@test.com");
        assertThat(user.getFirstName()).isEqualTo("test");
        assertThat(user.getLastName()).isEqualTo("test");
        assertThat(user.getNickname()).isEqualTo("test");
        assertThat(user.getPassword()).isEqualTo("encodedpassword");
    }

}