package com.edu.ulab.app.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data//(staticConstructor = "id")
public class UserEntity {
    private static long increment = 0;
    //@Setter(AccessLevel.NONE)
    private Long id;
    private String fullName;
    private String title;
    private int age;
    private List<BookEntity> listBooks = new ArrayList<>();

//    public UserEntity() {
//        this.id = increment++;
//    }
}
