package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Storage {
    private static long userIncrement = 0;
    private static long bookIncrement = 0;
    List<UserEntity> listUsersEntity = new ArrayList<>();
    List<BookEntity> listBookEntity = new ArrayList<>();

    public UserEntity saveUser(UserEntity user) {
        try {
            user.setId(userIncrement++);
            listUsersEntity.add(user);
            return user;
        } catch (Exception ignored) {
            throw new NotFoundException("User not saved!");
        }
    }

    public BookEntity saveBook(BookEntity book) {
        try {
            book.setId(bookIncrement++);
            listBookEntity.add(book);
            addBookToUser(book);
            return book;
        } catch (Exception ignored) {
            throw new NotFoundException("Book not saved!");
        }
    }

    public UserEntity updateUser(UserEntity user) {
        UserEntity gotUser = getUserById(user.getId());
        if (gotUser == null) {
            gotUser = saveUser(user);
        } else {
            gotUser.setFullName(user.getFullName());
            gotUser.setTitle(user.getTitle());
            gotUser.setAge(user.getAge());
        }
        return gotUser;
    }

    public UserEntity getUserById(Long id) {
        if (id == null) {
            return null;
        }

        return listUsersEntity.stream()
                .filter(u -> id.equals(u.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<BookEntity> getUserBooks(Long id) {
        UserEntity user = getUserById(id);
        if (user == null) {
            throw new NotFoundException("User id=" + id + " not found!");
        }
        return user.getListBooks();
    }

    public void deleteUserById(Long id) {
        if (!listUsersEntity.remove(getUserById(id))) {
            throw new NotFoundException("User id=" + id + " not found!");
        }
    }

    public void deleteBookById(Long id) {
        if (id == null) {
            return;
        }
        listBookEntity.remove(listBookEntity.stream()
                .filter(b -> id.equals(b.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Book not found!")));
    }

    private void addBookToUser(BookEntity book) {
        UserEntity userEntity = listUsersEntity.stream()
                .filter(e -> book.getUserId() == (e.getId()))
                .findFirst()
                .orElse(null);
        assert userEntity != null;
        userEntity.getListBooks().add(book);
    }
}
