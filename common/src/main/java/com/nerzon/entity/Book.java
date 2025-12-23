package com.nerzon.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor // Генерирует public Book() {}
@AllArgsConstructor // Генерирует конструктор со всеми полями
@EqualsAndHashCode
@ToString
public class Book {
    private String id;
    private String title;
    private String author;
}