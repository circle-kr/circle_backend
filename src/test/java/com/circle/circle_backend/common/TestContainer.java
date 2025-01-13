package com.circle.circle_backend.common;

import com.circle.circle_backend.user.controller.UserCreateController;
import com.circle.circle_backend.user.mock.FakeUserRepository;
import com.circle.circle_backend.user.service.UserRepository;
import com.circle.circle_backend.user.service.UserServiceImpl;

public class TestContainer {

    public final UserCreateController userCreateController;
    public final UserRepository userRepository;
    public final UserServiceImpl userServiceImpl;

    public TestContainer() {
        this.userRepository = new FakeUserRepository();
        this.userServiceImpl = UserServiceImpl.builder()
                .userRepository(this.userRepository)
                .build();
        this.userCreateController = UserCreateController.builder()
                .userService(this.userServiceImpl)
                .build();
    }
}
