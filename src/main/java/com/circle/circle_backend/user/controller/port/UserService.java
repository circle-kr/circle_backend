package com.circle.circle_backend.user.controller.port;

import com.circle.circle_backend.user.dto.request.UserCreateRequest;
import com.circle.circle_backend.user.dto.request.UserEmailCheckRequest;
import com.circle.circle_backend.user.dto.request.UserNicknameCheckRequest;
import com.circle.circle_backend.user.dto.request.UserPatchRequest;
import com.circle.circle_backend.user.dto.response.*;

public interface UserService {

    UserResponse create(UserCreateRequest userCreateRequest);

    MyInfoResponse readMyInfo(Long userId);

    MyInfoResponse patch(Long userId, UserPatchRequest userPatchRequest);

    UserInfoResponse readUserInfo(Long userId);

    UserEmailCheckResponse checkEmail(UserEmailCheckRequest userEmailCheckRequest);

    UserNicknameCheckResponse checkNickname(UserNicknameCheckRequest userNicknameCheckRequest);
}
