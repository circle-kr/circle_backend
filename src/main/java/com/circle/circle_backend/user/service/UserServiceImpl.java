package com.circle.circle_backend.user.service;

import com.circle.circle_backend.common.response.responseEnum.ErrorResponseEnum;
import com.circle.circle_backend.exception.impl.ResourceException;
import com.circle.circle_backend.security.utils.PasswordUtils;
import com.circle.circle_backend.user.controller.port.UserService;
import com.circle.circle_backend.user.dto.request.UserCreateRequest;
import com.circle.circle_backend.user.dto.request.UserEmailCheckRequest;
import com.circle.circle_backend.user.dto.request.UserNicknameCheckRequest;
import com.circle.circle_backend.user.dto.request.UserPatchRequest;
import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.dto.response.*;
import com.circle.circle_backend.user.service.port.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordUtils passwordUtils;

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        if (userRepository.existsByEmail(userCreateRequest.getEmail())) {
            throw new ResourceException(ErrorResponseEnum.DUPLICATED_RESOURCE);
        }
        if (userRepository.existsByNickname(userCreateRequest.getNickname())) {
            throw new ResourceException(ErrorResponseEnum.DUPLICATED_RESOURCE);
        }

        String encodedPassword = passwordUtils.encode(userCreateRequest.getPassword());
        User user = User.builder()
                .firstName(userCreateRequest.getFirstName())
                .lastName(userCreateRequest.getLastName())
                .nickname(userCreateRequest.getNickname())
                .email(userCreateRequest.getEmail())
                .password(encodedPassword)
                .build();
        userRepository.save(user);

        return UserResponse.from(user);
    }

    @Override
    public MyInfoResponse readMyInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        if (!userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND);
        }

        return MyInfoResponse.from(user);
    }

    @Override
    public MyInfoResponse patch(Long userId, UserPatchRequest userPatchRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        if (!userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND);
        }
        user.patch(userPatchRequest);

        return MyInfoResponse.from(user);
    }

    @Override
    public UserInfoResponse readUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        return UserInfoResponse.from(user);
    }

    @Override
    public UserEmailCheckResponse checkEmail(UserEmailCheckRequest userEmailCheckRequest) {
        if (userRepository.existsByEmail(userEmailCheckRequest.getEmail())) {
            throw new ResourceException(ErrorResponseEnum.DUPLICATED_RESOURCE);
        }
        return UserEmailCheckResponse.from(userEmailCheckRequest);
    }

    @Override
    public UserNicknameCheckResponse checkNickname(UserNicknameCheckRequest userNicknameCheckRequest) {
        if (userRepository.existsByNickname(userNicknameCheckRequest.getNickname())) {
            throw new ResourceException(ErrorResponseEnum.DUPLICATED_RESOURCE);
        }
        return UserNicknameCheckResponse.from(userNicknameCheckRequest);
    }
}
