package com.edu.ulab.app.mapper;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookEntityMapper {
    BookEntity userDtoToUserEntity(BookDto bookDto);

    BookDto userEntityToUserDto(BookEntity bookEntity);
}