package com.circle.circle_backend.user.service;

import com.circle.circle_backend.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);

}
