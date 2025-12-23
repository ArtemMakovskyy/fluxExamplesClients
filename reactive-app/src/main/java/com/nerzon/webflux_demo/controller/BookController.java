package com.nerzon.webflux_demo.controller;

import com.nerzon.dto.BookDto;
import com.nerzon.entity.Book;
import com.nerzon.mapper.BookMapper;
import com.nerzon.webflux_demo.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookRepo bookRepo;
    private final BookMapper bookMapper;

    @GetMapping("/{id}")
    public Mono<BookDto> getBookById(@PathVariable String id) {
        log.info("currentTimeMillis: {}", System.currentTimeMillis());
        return bookRepo.findById(id)
                .map(bookMapper::toDto);
    }

    @GetMapping
    public Flux<BookDto> getAllBooks() {
        return bookRepo.findAll()
                .map(bookMapper::toDto);
    }

    @PostMapping
    public Mono<BookDto> addNewBook(@RequestBody BookDto dto) {
        log.info("currentTimeMillis: {}", System.currentTimeMillis());
        return bookRepo.save(bookMapper.toEntity(dto))
                .map(bookMapper::toDto);
    }
}
