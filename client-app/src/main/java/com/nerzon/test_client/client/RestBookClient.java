package com.nerzon.test_client.client;

import com.nerzon.entity.Book;
import com.nerzon.test_client.service.BookFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestBookClient {

    private final RestTemplate restTemplate;
    private final BookFactory bookFactory;

    private int bookId = 10;

    public void pushBooks(int count) {
        for (int i = 0; i <= count; i++) {
            restTemplate.postForEntity(
                    "http://localhost:9090/api/books",
                    bookFactory.create(bookId++),
                    Book.class
            );
        }
    }

    public void pullBooks(int fromId) {
        for (int i = fromId; i < bookId; i++) {
            restTemplate.getForEntity(
                    "http://localhost:9090/api/books/{id}",
                    Book.class,
                    i
            );
        }
    }
}
