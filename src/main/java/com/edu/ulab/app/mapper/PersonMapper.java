package com.edu.ulab.app.mapper;

import com.edu.ulab.app.entity.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong("ID"));
        person.setTitle(rs.getString("TITLE"));
        person.setFullName(rs.getString("FULL_NAME"));
        person.setAge(rs.getInt("AGE"));
        return person;
    }
}
