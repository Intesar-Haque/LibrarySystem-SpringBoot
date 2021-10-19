package com.example.library.service;

import com.example.library.model.Users;
import com.example.library.model.dto.UsersDto;

public interface UsersService {
    Users addUser(UsersDto usersDto);
    Users removeUser(Long userId);
    String login(UsersDto usersDto, String auth);
    String generateRandom(String username);


}
