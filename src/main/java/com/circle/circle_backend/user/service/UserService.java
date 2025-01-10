package com.circle.circle_backend.user.service;

import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.domain.UserCreateDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User create(UserCreateDto userCreateDto) {
        User user = User.builder()
                .firstName(userCreateDto.getFirstName())
                .lastName(userCreateDto.getLastName())
                .email(userCreateDto.getEmail())
                .password(userCreateDto.getPassword())
                .nickName(userCreateDto.getNickName())
                .build();
        user = userRepository.save(user);
        return user;
    }
}
