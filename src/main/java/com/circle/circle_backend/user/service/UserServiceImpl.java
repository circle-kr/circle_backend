package com.circle.circle_backend.user.service;

import com.circle.circle_backend.user.controller.UserService;
import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.domain.UserCreateRequest;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Builder
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(UserCreateRequest userCreateRequest) {
        User user = User.from(userCreateRequest);
        user = userRepository.save(user);
        return user;
    }
}
