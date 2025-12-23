package com.nerzon.test_client.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomTextGeneratorImpl implements RandomTextGenerator {

    private static final String SOURCE =
            "qwertyuiopasdfghjklzxcvbnm1234567890 qwertyuiopasdfghjklzxcvbnm";

    @Override
    public String randomText(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(SOURCE.charAt(ThreadLocalRandom.current().nextInt(SOURCE.length())));
        }
        return sb.toString();
    }
}