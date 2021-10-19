package com.example.library.service;

import com.example.library.model.Users;
import com.example.library.model.dto.UsersDto;
import org.springframework.http.ResponseEntity;

public interface UsersService {
    ResponseEntity<String> addUser(UsersDto usersDto);
    ResponseEntity<String>  removeUser(Long userId, String auth);
    ResponseEntity<String>  login(UsersDto usersDto, String auth);
    String generateRandom(String username);


}
