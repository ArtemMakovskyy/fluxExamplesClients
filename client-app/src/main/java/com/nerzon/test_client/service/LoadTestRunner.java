package com.nerzon.test_client.service;

import com.nerzon.test_client.client.ReactiveBookClient;
import com.nerzon.test_client.client.RestBookClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoadTestRunner implements CommandLineRunner {

    private final ReactiveBookClient reactiveClient;
    private final RestBookClient restClient;
    private final BookFactory bookFactory;

    @Override
    public void run(String... args) {
        runReactiveTest();
        pauseWithCountdown();
        runRestTest();
    }

    private void runReactiveTest() {
        long start = System.nanoTime();

        Flux.range(10, 500)
                .flatMap(i -> reactiveClient.saveBook(bookFactory.create(i)))
                .blockLast();

        Flux.range(10, 500)
                .flatMap(reactiveClient::saveBook)
                .blockLast();

        long finish = System.nanoTime();
        log.debug("Reactive result: " + (finish - start) / 1_000_000 + "ms");
    }

    private void runRestTest() {
        long start = System.nanoTime();

        for (int i = 10; i < 510; i++) {
            restClient.saveBook(bookFactory.create(i));
        }

        for (int i = 10; i < 510; i++) {
            restClient.saveBook(i);
        }

        long finish = System.nanoTime();
        log.debug("Rest result: " + (finish - start) / 1_000_000 + "ms");
    }

    private void pauseWithCountdown() {
        for (int i = 0; i < 10; i++) {
            log.debug("" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
