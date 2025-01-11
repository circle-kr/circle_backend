package com.circle.circle_backend.user.service;

import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.domain.UserCreateDto;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Builder
public class UserService {

    private final UserRepository userRepository;

    public User create(UserCreateDto userCreateDto) {
        User user = User.from(userCreateDto);
        user = userRepository.save(user);
        return user;
    }
}
