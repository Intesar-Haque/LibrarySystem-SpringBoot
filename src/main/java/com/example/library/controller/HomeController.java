package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.service.BooksService;
import com.example.library.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    BooksService booksService;
    @Autowired
    UsersService usersService;

    @RequestMapping("/home")
    public String loggedInHome (Model model, Authentication authentication) {
        List<Books> books = booksService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("user",authentication.getName());

        return "home";
    }

    @RequestMapping("/")
    public String home (Model model) {
        List<Books> books = booksService.getAllBooks();
        model.addAttribute("books", books);
        return "index";
    }
    @RequestMapping("/admin")
    public String admin (Model model, Authentication authentication) {
        List<Books> books = booksService.getAllBooks();
        model.addAttribute("books", books);
        return "admin";
    }




}
