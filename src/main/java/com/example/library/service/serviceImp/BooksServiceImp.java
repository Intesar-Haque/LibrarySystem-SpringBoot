package com.example.library.service.serviceImp;

import com.example.library.model.Books;
import com.example.library.model.Users;
import com.example.library.model.dto.BooksDto;
import com.example.library.repo.BooksRepo;
import com.example.library.repo.UsersRepo;
import com.example.library.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BooksServiceImp implements BooksService {
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    UsersRepo usersRepo;

    @Override
    public ResponseEntity<String> addBook(BooksDto booksDto, String auth) {
        Users user = usersRepo.findByAuth(auth);
        if (user != null) {
            if (Objects.equals(user.getRole(), "admin")) {
                Books books = new Books();
                books.setName(booksDto.getName());
                books.setYear(booksDto.getYear());
                books.setAuthor(booksDto.getAuthor());
                books.setGenre(booksDto.getGenre());
                booksRepo.save(books);
                return  new ResponseEntity<>("Book added", HttpStatus.OK);
            }
            return new ResponseEntity<>("Role denied", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<String>  removeBook(Long bookId, String auth) {
        Users user = usersRepo.findByAuth(auth);
        if(Objects.nonNull(user)){
            if(Objects.equals(user.getRole(),"admin")){
                Books books = booksRepo.findByBookId(bookId);
                List<Users> emptyList = new ArrayList<>();
                books.setUsers(emptyList);
                booksRepo.save(books);
                booksRepo.deleteByBookId(bookId);
                return  new ResponseEntity<>("Book Removed", HttpStatus.OK);
            }
            return new ResponseEntity<>("Role denied", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<String>  borrowBook(Long bookId, String auth) {
        Users user = usersRepo.findByAuth(auth);
        if (Objects.nonNull(user)) {
            if (Objects.equals(user.getRole(), "customer")) {
                Books book = booksRepo.findByBookId(bookId);
                List<Users> usersList = book.getUsers();
                usersList.add(user);
                book.setUsers(usersList);
                booksRepo.save(book);
                return  new ResponseEntity<>(book.getName() + " borrowed by " + user.getName(), HttpStatus.OK);
            }
            return new ResponseEntity<>("Role denied", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
    }
}
