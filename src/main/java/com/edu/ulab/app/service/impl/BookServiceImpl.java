package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.repository.BookRepository;
import com.edu.ulab.app.repository.BookRepositoryStorage;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.bookDtoToBook(bookDto);
        log.info("Mapped book: {}", book);
        Book savedBook = bookRepository.save(book);
        log.info("Saved book: {}", savedBook);
        return bookMapper.bookToBookDto(savedBook);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book book = bookMapper.bookDtoToBook(bookDto);
        log.info("Mapped book: {}", book);
        Book savedBook = bookRepository.save(book);
        log.info("Updated book: {}", savedBook);
        return null;
    }

    @Override
    public BookDto getBookById(Long id) {
        // реализовать недстающие методы
        //нет endpoints, где передается id книги
        return null;
    }

    @Override
    public List<BookDto> getBooksByUserId(Long userId) {
        return  bookRepository.findBooksByUserId(userId).stream()
                .map(bookMapper::bookToBookDto)
                .peek(bookDto -> log.info("Mapped BookDto from list Book: {}", bookDto))
                .toList();
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
        log.info("Deleted user with id=: {}", id);
    }
}
