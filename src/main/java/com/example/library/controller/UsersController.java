package com.example.library.controller;

import com.example.library.model.dto.UsersDto;
import com.example.library.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    @Autowired
    UsersService usersService;
    @RequestMapping("/user/add")
    public ResponseEntity<String> addUser(@RequestBody UsersDto usersDto) {
        return usersService.addUser(usersDto);
    }
    @RequestMapping("/user/remove")
    public ResponseEntity<String> removeUser(@RequestParam Long userId, @RequestHeader String auth) {
        return usersService.removeUser(userId, auth);
    }
    @RequestMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody UsersDto usersDto, @RequestHeader String auth) {
        return usersService.login(usersDto, auth);
    }
}
