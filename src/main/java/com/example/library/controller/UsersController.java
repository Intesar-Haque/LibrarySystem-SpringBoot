package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.model.Users;
import com.example.library.model.dto.BooksDto;
import com.example.library.model.dto.UsersDto;
import com.example.library.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user/")
public class UsersController {
    @Autowired
    UsersService usersService;
    @RequestMapping("add")
    public Users addUser(@RequestBody UsersDto usersDto) {
        return usersService.addUser(usersDto);
    }
    @RequestMapping("remove")
    public Users removeUser(@RequestParam Long userId) {
        return usersService.removeUser(userId);
    }
}
