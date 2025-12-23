package com.nerzon.test_client;

import com.nerzon.test_client.service.LoadTestRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Активує Feign
@RequiredArgsConstructor
public class ClientApplication implements CommandLineRunner {

    private final LoadTestRunner loadTestRunner;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        loadTestRunner.run(args);
    }
}