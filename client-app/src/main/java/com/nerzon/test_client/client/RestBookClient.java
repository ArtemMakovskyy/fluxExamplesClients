package com.nerzon.test_client.client;

import com.nerzon.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestBookClient {

    private final RestTemplate restTemplate;

    public BookDto saveBook(BookDto book) {
        return restTemplate.postForObject(
                "http://localhost:8080/api/books",
                book,
                BookDto.class
        );
    }

    public BookDto getBook(String id) {
        return restTemplate.getForObject(
                "http://localhost:8080/api/books/{id}",
                BookDto.class,
                id
        );
    }
}
