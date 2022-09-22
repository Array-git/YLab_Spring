package com.edu.ulab.app.repository;

import com.edu.ulab.app.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    BookEntity createBook(BookEntity book);

    Optional<BookEntity> deleteBook(Long bookId);

    List<BookEntity> getBooksByUserId(Long userId);
}
