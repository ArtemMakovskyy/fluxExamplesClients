package com.nerzon.mapper;

import com.nerzon.dto.BookDto;
import com.nerzon.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor()
        );
    }

    public Book toEntity(BookDto dto) {
        return new Book(
                dto.getId(),
                dto.getTitle(),
                dto.getAuthor()
        );
    }
}
