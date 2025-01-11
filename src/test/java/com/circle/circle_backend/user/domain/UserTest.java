package com.circle.circle_backend.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    public void User를_UserCreateDto로_생성할_수_있다() {
        // given
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .email("test@test.com")
                .firstName("test")
                .lastName("test")
                .nickname("test")
                .password("test")
                .build();

        // when
        User user = User.from(userCreateDto);

        // then
        assertThat(user.getEmail()).isEqualTo("test@test.com");
        assertThat(user.getFirstName()).isEqualTo("test");
        assertThat(user.getLastName()).isEqualTo("test");
        assertThat(user.getNickname()).isEqualTo("test");
        assertThat(user.getPassword()).isEqualTo("test");
    }

    // TODO: 비밀번호 암호화하여 저장 테스트
}