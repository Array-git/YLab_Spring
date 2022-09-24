package com.edu.ulab.app.repository;

import com.edu.ulab.app.entity.UserEntity;

public interface UserRepositoryStorage {
    UserEntity save(UserEntity user);

    UserEntity findUserById(Long userId);

    void deleteUser(Long userId);
}
