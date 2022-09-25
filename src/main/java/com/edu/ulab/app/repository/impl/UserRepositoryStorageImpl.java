package com.edu.ulab.app.repository.impl;

import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.repository.UserRepositoryStorage;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryStorageImpl implements UserRepositoryStorage {
    private final Storage storage;

    public UserRepositoryStorageImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public UserEntity save(UserEntity user) {
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
