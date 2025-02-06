package com.circle.circle_backend.common;

import com.circle.circle_backend.user.controller.UserCreateController;
import com.circle.circle_backend.user.controller.port.UserService;
import com.circle.circle_backend.user.mock.FakePasswordUtils;
import com.circle.circle_backend.user.mock.FakeUserRepository;
import com.circle.circle_backend.user.service.UserRepository;
import com.circle.circle_backend.user.service.UserServiceImpl;

public class TestContainer {

    public final UserCreateController userCreateController;
    public final UserRepository userRepository;
    public final UserService userService;

    public TestContainer() {
        this.userRepository = new FakeUserRepository();
        this.userService = UserServiceImpl.builder()
                .userRepository(this.userRepository)
                .passwordUtils(new FakePasswordUtils())
                .build();
        this.userCreateController = UserCreateController.builder()
                .userService(this.userService)
                .build();
    }
}
