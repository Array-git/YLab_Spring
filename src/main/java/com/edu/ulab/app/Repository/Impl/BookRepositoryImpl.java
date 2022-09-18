package com.edu.ulab.app.Repository.Impl;

import com.edu.ulab.app.Repository.BookRepository;
import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void deleteBook(Long bookId) {
        storage.deleteBookById(bookId);
    }

    @Override
    public List<BookEntity> getBooksByUserId(Long userId) {
        return storage.getUserBooks(userId);
    }
}
