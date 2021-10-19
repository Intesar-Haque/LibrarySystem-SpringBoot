package com.example.library.service;

import com.example.library.model.Books;
import com.example.library.model.dto.BooksDto;

public interface BooksService {
    String addBook(BooksDto booksDto, String auth);
    Books removeBook(Long bookId);
    String borrowBook(Long userId, Long bookId, String auth);
}
