package com.example.library.service;

import com.example.library.model.Books;
import com.example.library.model.dto.BooksDto;
import org.springframework.http.ResponseEntity;

public interface BooksService {
    ResponseEntity<String> addBook(BooksDto booksDto, String auth);
    ResponseEntity<String>  removeBook(Long bookId, String auth);
    ResponseEntity<String>  borrowBook(Long bookId, String auth);
}
