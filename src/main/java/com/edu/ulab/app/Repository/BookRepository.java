package com.edu.ulab.app.Repository;

import com.edu.ulab.app.entity.BookEntity;

import java.util.List;

public interface BookRepository {
    BookEntity createBook(BookEntity book);

    void deleteBook(Long bookId);

    List<BookEntity> getBooksByUserId(Long userId);
}
