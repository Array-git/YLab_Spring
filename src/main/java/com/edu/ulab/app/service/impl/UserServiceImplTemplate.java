package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Person;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImplTemplate implements UserService {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public UserServiceImplTemplate(JdbcTemplate jdbcTemplate,
                                   UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        final String INSERT_SQL = "INSERT INTO PERSON(FULL_NAME, TITLE, AGE) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"id"});
                    ps.setString(1, userDto.getFullName());
                    ps.setString(2, userDto.getTitle());
                    ps.setLong(3, userDto.getAge());
                    return ps;
                }, keyHolder);

        userDto.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        log.info("Saved user: {}", userDto);
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserDto gotUser;
        try {
            gotUser = getUserById(userDto.getId());
            jdbcTemplate.update("UPDATE PERSON SET TITLE=?, FULL_NAME=?, AGE=? WHERE id=?",
                    userDto.getTitle(), userDto.getFullName(), userDto.getAge(), userDto.getId());
            log.info("Updated user: {}", userDto);
        } catch (NotFoundException ignored) {
            gotUser = createUser(userDto);
        }
        return gotUser;
    }

    @Override
    public UserDto getUserById(Long id) {
        final String SELECT_BY_ID = "SELECT * FROM PERSON WHERE id=?";
        Person person = jdbcTemplate.query(SELECT_BY_ID, new BeanPropertyRowMapper<>(Person.class), id)
                .stream()
                .findAny()
                .orElseThrow(() -> new NotFoundException("User not found!"));
        log.info("Found user: {}", person);
        return userMapper.personToUserDto(person);
    }

    @Override
    public void deleteUserById(Long id) {
        if (getUserById(id) != null) {
            final String DELETE_BY_ID = "DELETE FROM PERSON WHERE ID=?";
            jdbcTemplate.update(DELETE_BY_ID, id);
            //log.info("Deleted user with id=: {}", id);
        }
    }
}
