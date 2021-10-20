package com.example.library.service;

import com.example.library.model.Books;
import com.example.library.model.Users;
import com.example.library.model.dto.UsersDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsersService {
    ResponseEntity<String> addUser(UsersDto usersDto);
    ResponseEntity<String>  removeUser(Long userId);
    List<Books> borrowed (String uname);
}
