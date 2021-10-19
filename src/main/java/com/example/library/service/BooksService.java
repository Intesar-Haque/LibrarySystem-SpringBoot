package com.example.library.service;

import com.example.library.model.Books;
import com.example.library.model.dto.BooksDto;

public interface BooksService {
    Books addBook(BooksDto booksDto);
    Books removeBook(Long bookId);
    Books borrowBook(Long userId, Long bookId);
}
