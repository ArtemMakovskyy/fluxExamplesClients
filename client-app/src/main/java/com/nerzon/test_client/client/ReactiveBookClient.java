package com.nerzon.test_client.client;

import com.nerzon.dto.BookDto;
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

    public Mono<BookDto> saveBook(BookDto book) {
        return webClient.post()
                .uri("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(book)
                .retrieve()
                .bodyToMono(BookDto.class);
    }

    public Mono<BookDto> getBook(int id) {
        return webClient.get()
                .uri("/api/books/{id}", id)
                .retrieve()
                .bodyToMono(BookDto.class)
                .doOnError(err -> log.error("Reactive error", err));
    }
}
