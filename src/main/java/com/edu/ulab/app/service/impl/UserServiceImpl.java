package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.Repository.UserRepository;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.mapper.UserEntityMapper;
import com.edu.ulab.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserEntityMapper userEntityMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserEntityMapper userEntityMapper,
                           UserRepository userRepository) {
        this.userEntityMapper = userEntityMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = userEntityMapper.userDtoToUserEntity(userDto);
        userEntity = userRepository.createUser(userEntity);
        userDto.setId(userEntity.getId());
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = userEntityMapper.userDtoToUserEntity(userDto);
        userDto = userEntityMapper.userEntityToUserDto(userRepository.updateUser(userEntity));
        //UserEntity userEntity = userRepository.findUserById(userDto.getId());
        //If user not exist, create new user
/*        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setTitle(userDto.getTitle());
            userEntity.setFullName(userDto.getFullName());
            userEntity.setAge(userDto.getAge());
            userRepository.createUser(userEntity);
        }*/
/*        userEntity.setTitle(userDto.getTitle());
        userEntity.setFullName(userDto.getFullName());
        userEntity.setAge(userDto.getAge());
        userDto.setId(userEntity.getId());*/
        return userDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findUserById(id);
        return userEntityMapper.userEntityToUserDto(userEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteUser(id);
    }
}
