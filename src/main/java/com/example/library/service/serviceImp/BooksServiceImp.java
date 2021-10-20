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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseEntity<String> addBook(BooksDto booksDto) {
        Books books = new Books();
        books.setName(booksDto.getName());
        books.setYear(booksDto.getYear());
        books.setAuthor(booksDto.getAuthor());
        books.setGenre(booksDto.getGenre());
        booksRepo.save(books);
        return  new ResponseEntity<>("Book added", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String>  removeBook(Long bookId) {
        Books books = booksRepo.findByBookId(bookId);
        List<Users> emptyList = new ArrayList<>();
        books.setUsers(emptyList);
        booksRepo.save(books);
        booksRepo.deleteByBookId(bookId);
        return  new ResponseEntity<>("Book Removed", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String>  borrowBook(Long bookId, String userId) {
        Users user = usersRepo.findByUname(userId).orElse(null);
        if (Objects.nonNull(user)) {
            Books book = booksRepo.findByBookId(bookId);
            List<Books> booksList = user.getBooks();
            if(booksList.contains(book)){
                return  new ResponseEntity<>(book.getName() + "is already borrowed by " + user.getName(), HttpStatus.CONFLICT);
            } else{
                List<Users> usersList = book.getUsers();
                usersList.add(user);
                book.setUsers(usersList);
                booksRepo.save(book);
                return  new ResponseEntity<>(book.getName() + " borrowed by " + user.getName(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public List<Books> getAllBooks() {
        return booksRepo.findAll();
    }
}
