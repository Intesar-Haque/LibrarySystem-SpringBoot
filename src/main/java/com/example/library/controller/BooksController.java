package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.model.dto.BooksDto;
import com.example.library.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BooksController {
    @Autowired
    BooksService booksService;

    @RequestMapping("/book/add")
    public ResponseEntity<String> addBook(@RequestBody BooksDto booksDto, @RequestHeader String auth) {
        return booksService.addBook(booksDto, auth);
    }
    @RequestMapping("/book/remove")
    public ResponseEntity<String> removeBook(@RequestParam Long bookId, @RequestHeader String auth) {
        return booksService.removeBook(bookId, auth);
    }
    @RequestMapping("/book/borrow")
    public  ResponseEntity<String> borrowBook(@RequestParam Long bookId, @RequestHeader String auth ){
        return booksService.borrowBook(bookId, auth);
    }

}
