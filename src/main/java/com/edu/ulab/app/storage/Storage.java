package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class Storage {
    private static long userIncrement = 0;
    private static long bookIncrement = 0;
    Map<Long, UserEntity> userStorage = new HashMap<>();
    Map<Long, BookEntity> bookStorage = new HashMap<>();

    public UserEntity saveUser(UserEntity user) {
        user.setId(userIncrement++);
        userStorage.put(user.getId(), user);
        return user;
    }

    public BookEntity saveBook(BookEntity book) {
        book.setId(bookIncrement++);
        bookStorage.put(book.getId(), book);
        addBookToUser(book);
        return book;
    }

    public UserEntity getUserById(Long id) {
        return userStorage.get(id);
    }

    public List<BookEntity> getUserBooks(Long id) {
        return userStorage.get(id).getListBooks();
    }

    public void deleteUserById(Long id) {
        userStorage.remove(id);
    }

    public Optional<BookEntity> deleteBookById(Long id) {
        return Optional.ofNullable(bookStorage.remove(id));
    }

    private void addBookToUser(BookEntity book) {
        UserEntity userEntity = userStorage.get(book.getUserId());
        userEntity.getListBooks().add(book);
    }
}
