package com.nerzon.test_client.client;

import com.nerzon.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReactiveBookClient {

    private final WebClient webClient;

    public Mono<Book> saveBook(Book book) {
        return webClient.post()
                .uri("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Book.class);
    }

    public Mono<Book> getBook(int id) {
        return webClient.get()
                .uri("/api/books/{id}", id)
                .retrieve()
                .bodyToMono(Book.class)
                .doOnError(err -> log.error("Reactive error", err));
    }
}
