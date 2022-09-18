package com.edu.ulab.app.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class BookEntity {
    private static long  increment=0;
    @Setter(AccessLevel.NONE)
    private Long id;
    private String title;
    private String author;
    private long pageCount;
    private long UserId;
    //private UserEntity User;

    public BookEntity() {
        this.id = increment++;
    }
}
