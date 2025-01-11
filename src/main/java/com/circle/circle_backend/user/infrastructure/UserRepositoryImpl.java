package com.circle.circle_backend.user.infrastructure;

import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.infrastructure.entity.UserEntity;
import com.circle.circle_backend.user.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        return userJpaRepository.save(UserEntity.from(user)).toUser();
    }
}
