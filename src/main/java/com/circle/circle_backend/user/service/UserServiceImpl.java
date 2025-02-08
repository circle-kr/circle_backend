package com.circle.circle_backend.user.service;

import com.circle.circle_backend.common.response.responseEnum.ErrorResponseEnum;
import com.circle.circle_backend.exception.impl.ResourceException;
import com.circle.circle_backend.security.utils.PasswordUtils;
import com.circle.circle_backend.user.controller.port.UserService;
import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.dto.request.UserCreateRequest;
import com.circle.circle_backend.user.dto.request.UserPatchRequest;
import com.circle.circle_backend.user.infrastructure.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Builder
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordUtils passwordUtils;

    @Override
    public User create(UserCreateRequest userCreateRequest) {
        if (userRepository.existsByEmail(userCreateRequest.getEmail())) {
            throw new ResourceException(ErrorResponseEnum.DUPLICATED_RESOURCE);
        }
        if (userRepository.existsByNickname(userCreateRequest.getNickname())) {
            throw new ResourceException(ErrorResponseEnum.DUPLICATED_RESOURCE);
        }
        String encodedPassword = passwordUtils.encode(userCreateRequest.getPassword());
        User user = User.from(userCreateRequest, encodedPassword);
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User readMyInfo(User user) {
        Long userId = user.getId();

        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND))
                .toUser();
    }

    @Override
    public User patch(User user, UserPatchRequest userPatchRequest) {
        Long userId = user.getId();
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        return userEntity.patch(userPatchRequest).toUser();
    }

    @Override
    public User readUserInfo(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND))
                .toUser();
    }
}
