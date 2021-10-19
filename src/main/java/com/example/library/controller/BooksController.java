package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.model.dto.BooksDto;
import com.example.library.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BooksController {
    @Autowired
    BooksService booksService;

    @RequestMapping("/book/add")
    public String addBook(@RequestBody BooksDto booksDto, @RequestHeader String auth) {
        return booksService.addBook(booksDto, auth);
    }
    @RequestMapping("/book/remove")
    public Books removeBook(@RequestParam Long bookId) {
        return booksService.removeBook(bookId);
    }
    @RequestMapping("/book/borrow")
    public  String borrowBook(@RequestParam Long userId, @RequestParam Long bookId, @RequestHeader String auth ){
        return booksService.borrowBook(userId, bookId, auth);
    }

}
