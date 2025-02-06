package com.circle.circle_backend.user.controller.port;

import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.dto.request.UserCreateRequest;
import com.circle.circle_backend.user.dto.request.UserPatchRequest;

public interface UserService {

    User create(UserCreateRequest userCreateRequest);

    User readMyInfo(User user);

    User patch(User user, UserPatchRequest userPatchRequest);

    User readUserInfo(Long userId);
}
