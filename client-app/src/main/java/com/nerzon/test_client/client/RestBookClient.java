package com.nerzon.test_client.client;

import com.nerzon.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestBookClient {

    private final RestTemplate restTemplate;

    public Book saveBook(Book book) {
        return restTemplate.postForObject(
                "http://localhost:8080/api/books",
                book,
                Book.class
        );
    }

    public Book saveBook(int id) {
        return restTemplate.getForObject(
                "http://localhost:8080/api/books/{id}",
                Book.class,
                id
        );
    }
}
