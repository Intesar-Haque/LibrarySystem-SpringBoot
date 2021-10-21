package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.model.dto.UsersDto;
import com.example.library.service.BooksService;
import com.example.library.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsersController {
    @Autowired
    UsersService usersService;
    @Autowired
    BooksService booksService;
    @PostMapping( "/registration/registerUser")
    public String registerUser(@ModelAttribute("usersDto") UsersDto usersDto) {
        usersService.addUser(usersDto);
        return "redirect:/home";
    }
    @RequestMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("usersDto", new UsersDto());
        return "registration";
    }
    @RequestMapping("/user/remove")
    public ResponseEntity<String> removeUser(@RequestParam Long userId, @RequestHeader String auth) {
        return usersService.removeUser(userId);
    }
    @RequestMapping("/user/books")
    public String borrowed(Model model, Authentication auth) {
        List<Books> books = usersService.borrowed(auth.getName());
        model.addAttribute("books", books);
        return "borrowedBooks";
    }
//    @RequestMapping("/user/books/remove")
//    public String borrowed(Authentication auth) {
//        List<Books> books = usersService.borrowed(auth.getName());
//        model.addAttribute("books", books);
//        return "borrowedBooks";
//    }
}
