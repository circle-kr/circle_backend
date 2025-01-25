package com.circle.circle_backend.user.controller;

import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.domain.UserCreateRequest;

public interface UserService {

    User create(UserCreateRequest userCreateRequest);

    User readMyInfo(User user);
}
