package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.BookEntityMapper;
import com.edu.ulab.app.repository.BookRepositoryStorage;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImplStorage implements BookService {

    private final BookEntityMapper bookEntityMapper;
    private final BookRepositoryStorage bookRepository;

    public BookServiceImplStorage(BookEntityMapper bookEntityMapper,
                                  BookRepositoryStorage bookRepository) {
        this.bookEntityMapper = bookEntityMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        BookEntity bookEntity = bookEntityMapper.userDtoToUserEntity(bookDto);
        bookEntity = bookRepository.save(bookEntity);
        bookDto.setId(bookEntity.getId());
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        return null;
    }

    @Override
    public BookDto getBookById(Long id) {
        return null;
    }

    @Override
    public List<BookDto> getBooksByUserId(Long userId) {
        List<BookEntity> listBooksOfUser = bookRepository.getBooksByUserId(userId);
        if (listBooksOfUser == null) {
            throw new NotFoundException("User with id=" + userId + " not found!");
        }
        List<BookDto> bookDtoList = new ArrayList<>();
        for (BookEntity book : listBooksOfUser) {
            bookDtoList.add(bookEntityMapper.userEntityToUserDto(book));
        }
        return bookDtoList;
    }

    @Override
    public void deleteBookById(Long id) {
        Optional.of(bookRepository.deleteBook(id)).get()
                .orElseThrow(() -> new NotFoundException("Book not found!"));
    }
}
