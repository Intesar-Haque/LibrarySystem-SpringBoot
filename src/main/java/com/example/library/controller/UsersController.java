package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.model.Users;
import com.example.library.model.dto.BooksDto;
import com.example.library.model.dto.UsersDto;
import com.example.library.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    @Autowired
    UsersService usersService;
    @RequestMapping("/user/add")
    public Users addUser(@RequestBody UsersDto usersDto) {
        return usersService.addUser(usersDto);
    }
    @RequestMapping("/user/remove")
    public Users removeUser(@RequestParam Long userId) {
        return usersService.removeUser(userId);
    }
    @RequestMapping("/user/login")
    public String login(@RequestBody UsersDto usersDto, @RequestHeader String auth) {
        return usersService.login(usersDto, auth);
    }
}
