package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.model.dto.BooksDto;
import com.example.library.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
    @Autowired
    BooksService booksService;

    @RequestMapping("/book/add")
    public Books addBook(@RequestBody BooksDto booksDto) {
        return booksService.addBook(booksDto);
    }
    @RequestMapping("/book/remove")
    public Books removeBook(@RequestParam Long bookId) {
        return booksService.removeBook(bookId);
    }

}
