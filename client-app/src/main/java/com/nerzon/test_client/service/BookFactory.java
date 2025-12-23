package com.nerzon.test_client.service;

import com.nerzon.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookFactory {

    private static final int TITLE_LENGTH = 10;
    private static final int AUTHOR_LENGTH = 10;

    private final RandomTextGenerator textGenerator;

    public Book create(int id) {
        return new Book(
                String.valueOf(id),
                textGenerator.randomText(TITLE_LENGTH),
                textGenerator.randomText(AUTHOR_LENGTH)
        );
    }
}
