package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.UserEntityMapper;
import com.edu.ulab.app.repository.UserRepositoryStorage;
import com.edu.ulab.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImplStorage implements UserService {

    private final UserEntityMapper userEntityMapper;
    private final UserRepositoryStorage userRepository;

    public UserServiceImplStorage(UserEntityMapper userEntityMapper,
                                  UserRepositoryStorage userRepository) {
        this.userEntityMapper = userEntityMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = userEntityMapper.userDtoToUserEntity(userDto);
        userEntity = userRepository.save(userEntity);
        userDto.setId(userEntity.getId());
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = userEntityMapper.userDtoToUserEntity(userDto);
        UserEntity gotUserEntity = userRepository.findUserById(userEntity.getId());
        if (gotUserEntity == null) {
            gotUserEntity = userRepository.save(userEntity);
        } else {
            gotUserEntity.setFullName(userEntity.getFullName());
            gotUserEntity.setTitle(userEntity.getTitle());
            gotUserEntity.setAge(userEntity.getAge());
        }
        userDto = userEntityMapper.userEntityToUserDto(gotUserEntity);
        return userDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findUserById(id);
        if (userEntity == null) {
            throw new NotFoundException("User with id=" + id + " not found!");
        }
        return userEntityMapper.userEntityToUserDto(userEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteUser(id);
    }
}
