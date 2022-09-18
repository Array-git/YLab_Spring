package com.edu.ulab.app.Repository.Impl;

import com.edu.ulab.app.Repository.UserRepository;
import com.edu.ulab.app.entity.UserEntity;
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
    public UserEntity updateUser(UserEntity user) {
        return storage.updateUser(user);
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
