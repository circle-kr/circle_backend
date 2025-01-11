package com.circle.circle_backend.user.service;

import com.circle.circle_backend.user.domain.User;

public interface UserRepository {

    User save(User user);
}
