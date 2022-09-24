package com.edu.ulab.app.repository.impl;

import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.repository.UserRepository;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImpl implements UserRepository {
    private final Storage storage;

    public UserRepositoryImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return storage.saveUser(user);
    }

    @Override
    public UserEntity findUserById(Long userId) {
        return storage.getUserById(userId);
    }

    @Override
    public void deleteUser(Long userId) {
        storage.deleteUserById(userId);
    }
}
