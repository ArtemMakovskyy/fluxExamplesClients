package com.nerzon.test_client.client;

import com.nerzon.entity.Book;
import com.nerzon.test_client.service.BookFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReactiveBookClient {

    private final WebClient webClient;
    private final BookFactory bookFactory;

    private int bookId = 10;

    public void pushBooks(int count) {
        for (int i = 0; i <= count; i++) {
            webClient.post()
                    .uri("/api/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(bookFactory.create(bookId++))
                    .retrieve()
                    .bodyToMono(Book.class)
                    .block();
        }
    }

    public void pullBooks(int fromId) {
        for (int i = fromId; i < bookId; i++) {
            webClient.get()
                    .uri("/api/books/{id}", i)
                    .retrieve()
                    .bodyToMono(String.class)
                    .subscribe(
                            ok -> {},
                            err -> log.error("Reactive error", err)
                    );
        }
    }
}
