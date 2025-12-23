// file: client-app/src/main/java/com/nerzon/test_client/service/LoadTestRunner.java
package com.nerzon.test_client.service;

import com.nerzon.test_client.client.ReactiveBookClient;
import com.nerzon.test_client.client.RestBookClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadTestRunner implements CommandLineRunner {

    private final ReactiveBookClient reactiveClient;
    private final RestBookClient restClient;

    @Override
    public void run(String... args) {
        runReactiveTest();
        pauseWithCountdown();
        runRestTest();
    }

    private void runReactiveTest() {
        long start = System.nanoTime();

        reactiveClient.pushBooks(500);
        reactiveClient.pullBooks(10);

        long finish = System.nanoTime();
        System.out.println("Reactive result: " + (finish - start) / 1_000_000 + "ms");
    }

    private void runRestTest() {
        long start = System.nanoTime();

        restClient.pushBooks(500);
        restClient.pullBooks(10);

        long finish = System.nanoTime();
        System.out.println("Rest result: " + (finish - start) / 1_000_000 + "ms");
    }

    private void pauseWithCountdown() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}