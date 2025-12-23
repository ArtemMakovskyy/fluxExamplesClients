package com.nerzon.test_client.service;

import com.nerzon.test_client.client.FeignBookClient;
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
    private final FeignBookClient feignClient;
    private final BookFactory bookFactory;

    @Override
    public void run(String... args) {
        log.info("=== STARTING LOAD TEST INTERFACE ===");

        // 1. Тест Reactive WebClient
        runReactiveTest();

        pause(5, "RestTemplate");

        // 2. Тест RestTemplate
        runRestTest();

        pause(5, "OpenFeign");

        // 3. Тест OpenFeign
        runFeignTest();

        log.info("=== ALL TESTS COMPLETED ===");
    }

    private void runReactiveTest() {
        log.info(">>> Starting Reactive Test (Non-blocking)...");
        long start = System.currentTimeMillis();

        // Збереження 500 книг
        Flux.range(10, 500)
                .flatMap(i -> reactiveClient.saveBook(bookFactory.create(i)))
                .blockLast();

        // Отримання 500 книг
        Flux.range(10, 500)
                .flatMap(reactiveClient::getBook)
                .blockLast();

        long end = System.currentTimeMillis();
        log.info("Reactive result: {} ms", (end - start));
    }

    private void runRestTest() {
        log.info(">>> Starting RestTemplate Test (Blocking)...");
        long start = System.currentTimeMillis();

        // Збереження 500 книг
        for (int i = 510; i < 1010; i++) {
            restClient.saveBook(bookFactory.create(i));
        }

        // Отримання 500 книг
        for (int i = 510; i < 1010; i++) {
            restClient.getBook(String.valueOf(i));
        }

        long end = System.currentTimeMillis();
        log.info("RestTemplate result: {} ms", (end - start));
    }

    private void runFeignTest() {
        log.info(">>> Starting OpenFeign Test (Declarative Blocking)...");
        long start = System.currentTimeMillis();

        // Збереження 500 книг
        for (int i = 1011; i < 1511; i++) {
            feignClient.saveBook(bookFactory.create(i));
        }

        // Отримання 500 книг
        for (int i = 1011; i < 1511; i++) {
            feignClient.getBook(String.valueOf(i));
        }

        long end = System.currentTimeMillis();
        log.info("OpenFeign result: {} ms", (end - start));
    }

    private void pause(int seconds, String nextTest) {
        log.info("Waiting {} seconds before starting {} test...", seconds, nextTest);
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread interrupted during pause");
        }
    }
}
