package com.example.library.service.serviceImp;

import com.example.library.model.Books;
import com.example.library.model.Users;
import com.example.library.model.dto.UsersDto;
import com.example.library.repo.BooksRepo;
import com.example.library.repo.UsersRepo;
import com.example.library.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class UsersServiceImp implements UsersService {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    BooksRepo booksRepo;
    @Override
    public ResponseEntity<String> addUser(UsersDto usersDto) {
        Users user = new Users();
        user.setName(usersDto.getName());
        user.setPass(usersDto.getPass());
        String auth = generateRandom(usersDto.getName());
        user.setAuth(auth);
        usersRepo.save(user);
        return  new ResponseEntity<>("User Created", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String>  removeUser(Long userId, String auth) {
        Users users = usersRepo.findByUserId(userId);
        if(users != null){
            if((Objects.equals(users.getAuth(), auth))){
                for (Books books : users.getBooks()){
                   books.getUsers().remove(users);
                }
                users.getBooks().removeAll(users.getBooks());
                usersRepo.save(users);
                usersRepo.deleteByUserId(userId);
                return  new ResponseEntity<>("User Removed", HttpStatus.OK);
            }
            return new ResponseEntity<>("Role denied", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<String> login(UsersDto usersDto, String auth) {
        Users user = usersRepo.findByNameAndPass(usersDto.getName(), usersDto.getPass());
        if(user != null){
            auth = generateRandom(user.getName());
            user.setAuth(auth);
            usersRepo.save(user);
            return  new ResponseEntity<>("Welcome "+user.getName()+", New Auth id : "+auth, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Username or Password", HttpStatus.UNAUTHORIZED);

    }

    @Override
    public String  generateRandom(String username) {
        Random random = new Random();
        int randomNum = random.nextInt(999999);
        String generatedString = String.valueOf(randomNum);
        try {
            generatedString = username + generatedString;
        } catch(NullPointerException e){
            System.out.print(e);
        }
        return generatedString;
    }
}
