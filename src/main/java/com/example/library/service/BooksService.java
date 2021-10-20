package com.example.library.service;

import com.example.library.model.Books;
import com.example.library.model.dto.BooksDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BooksService {
    ResponseEntity<String> addBook(BooksDto booksDto);
    ResponseEntity<String> updateBook(BooksDto booksDto, Long bookId);
    ResponseEntity<String>  removeBook(Long bookId);
    ResponseEntity<String>  borrowBook(Long bookId, String userId);
    List<Books> getAllBooks();

}
