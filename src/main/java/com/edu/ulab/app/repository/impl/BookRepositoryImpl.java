package com.edu.ulab.app.repository.impl;

import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.repository.BookRepository;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookRepositoryImpl implements BookRepository {
    private final Storage storage;

    public BookRepositoryImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public BookEntity createBook(BookEntity book) {
        return storage.saveBook(book);
    }

    @Override
    public Optional<BookEntity> deleteBook(Long bookId) {
        return storage.deleteBookById(bookId);
    }

    @Override
    public List<BookEntity> getBooksByUserId(Long userId) {
        if (storage.getUserById(userId) == null) {
            return null;
        }
        return storage.getUserBooks(userId);
    }
}
