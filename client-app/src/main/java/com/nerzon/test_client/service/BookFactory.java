package com.nerzon.test_client.service;


import com.nerzon.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookFactory {

    private final RandomTextGenerator textGenerator;

    public Book create(int id) {
        return new Book(
                String.valueOf(id),
                textGenerator.randomText(10),
                textGenerator.randomText(15)
        );
    }
}
