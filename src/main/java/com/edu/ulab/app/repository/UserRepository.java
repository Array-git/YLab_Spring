package com.edu.ulab.app.repository;

import com.edu.ulab.app.entity.UserEntity;

public interface UserRepository {
    UserEntity createUser(UserEntity user);

    UserEntity updateUser(UserEntity user);

    UserEntity findUserById(Long userId);

    void deleteUser(Long userId);
}
