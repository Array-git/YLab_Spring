package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BookServiceImplTemplate implements BookService {

    private final JdbcTemplate jdbcTemplate;
    private final BookMapper bookMapper;

    public BookServiceImplTemplate(JdbcTemplate jdbcTemplate,
                                   BookMapper bookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        final String INSERT_SQL = "INSERT INTO BOOK(TITLE, AUTHOR, PAGE_COUNT, USER_ID) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(INSERT_SQL, new String[]{"id"});
                        ps.setString(1, bookDto.getTitle());
                        ps.setString(2, bookDto.getAuthor());
                        ps.setLong(3, bookDto.getPageCount());
                        ps.setLong(4, bookDto.getUserId());
                        return ps;
                    }
                },
                keyHolder);
        bookDto.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        log.info("Saved book: {}", bookDto);
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
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
        final String SELECT_BY_ID = "SELECT * FROM BOOK WHERE USER_ID=?";
        List<BookDto> bookList = jdbcTemplate.query(SELECT_BY_ID, new BeanPropertyRowMapper<>(Book.class), userId)
                .stream()
                .peek(b -> log.info("Found book from userId={}: {}", userId, b))
                .map(bookMapper::bookToBookDto)
                .peek(bookDto -> log.info("Mapped BookDto from list Book: {}", bookDto))
                .toList();
        return bookList;
    }

    @Override
    public void deleteBookById(Long id) {
        final String DELETE_BY_ID = "DELETE FROM BOOK WHERE ID=?";
        jdbcTemplate.update(DELETE_BY_ID, id);
        //log.info("Deleted book with id=: {}", id);
    }
}
