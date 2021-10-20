package com.example.library.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BooksDto {
    public BooksDto(String name, String year, String author, String genre) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }

    String name;
    String year;
    String author;
    String genre;
}
