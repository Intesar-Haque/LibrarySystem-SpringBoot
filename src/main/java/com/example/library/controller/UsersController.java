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
    @RequestMapping(path = "/registration/registerUser", method= RequestMethod.POST )
    public String registerUser(
            @RequestParam("name") String name,
            @RequestParam("uname") String uname,
            @RequestParam("pass") String pass
    ) {
        UsersDto usersDto = new UsersDto(name, pass, uname);
        usersService.addUser(usersDto);

        return "redirect:/";
    }
    @RequestMapping("/registration")
    public String registration() {
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
}
