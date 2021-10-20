package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.model.dto.BooksDto;
import com.example.library.repo.BooksRepo;
import com.example.library.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BooksController {
    @Autowired
    BooksService booksService;
    @Autowired
    BooksRepo booksRepo;
    @RequestMapping("/book/create")
    public String book () {
        return "book";
    }

    @RequestMapping("/book/update")
    public String updateBook (Model model,@RequestParam Long bookId) {
        Books book = booksRepo.findByBookId(bookId);
        model.addAttribute("book", book);
        return "bookUpdate";
    }
    @RequestMapping(path = "/book/update/confirm", method= RequestMethod.POST )
    public ResponseEntity<String> updateBook(
            @RequestParam("bookId") Long bookId,
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("genre") String genre,
            @RequestParam("year") String year ) {
        BooksDto booksDto = new BooksDto(name,year,author,genre);
        return booksService.updateBook(booksDto, bookId);
    }

    @RequestMapping(path = "/book/add", method= RequestMethod.POST )
    public ResponseEntity<String> addBook(
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("genre") String genre,
            @RequestParam("year") String year ) {
        BooksDto booksDto = new BooksDto(name,year,author,genre);
        return booksService.addBook(booksDto);
    }

    @RequestMapping("/book/remove")
    public ResponseEntity<String> removeBook(@RequestParam Long bookId) {
        return booksService.removeBook(bookId);
    }
    @RequestMapping("/book/borrow")
    public  ResponseEntity<String> borrowBook(@RequestParam Long bookId, Authentication auth){
        String userId = auth.getName();
        return booksService.borrowBook(bookId, userId);
    }

}
