package com.nerzon.rest_api.controller;


import com.nerzon.dto.BookDto;
import com.nerzon.entity.Book;
import com.nerzon.mapper.BookMapper;
import com.nerzon.rest_api.repository.BookRepo;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookRepo bookRepo;
    private final BookMapper bookMapper;

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable String id) {
        log.info("currentTimeMillis: {}", System.currentTimeMillis());
        Book book = bookRepo.findById(id);
        return bookMapper.toDto(book);
    }

    @GetMapping
    public Collection<BookDto> getAllBooks() {
        return bookRepo.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public BookDto addNewBook(@RequestBody BookDto dto) {
        log.info("currentTimeMillis: {}", System.currentTimeMillis());
        Book saved = bookRepo.save(bookMapper.toEntity(dto));
        return bookMapper.toDto(saved);
    }
}
