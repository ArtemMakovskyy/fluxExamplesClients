package com.nerzon.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Book {
    private String id;
    private String title;
    private String author;
}