package com.example.library.service.serviceImp;

import com.example.library.model.Books;
import com.example.library.model.Users;
import com.example.library.model.dto.BooksDto;
import com.example.library.repo.BooksRepo;
import com.example.library.repo.UsersRepo;
import com.example.library.service.BooksService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class BooksServiceImp implements BooksService {
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    UsersRepo usersRepo;

    @Override
    public String addBook(BooksDto booksDto, String auth) {
        Users user = usersRepo.findByAuth(auth);
        if(user != null){
            if(Objects.equals(user.getRole(), "admin")){
                Books books = new Books();
                books.setName(booksDto.getName());
                books.setYear(booksDto.getYear());
                books.setAuthor(booksDto.getAuthor());
                books.setGenre(booksDto.getGenre());
                booksRepo.save(books);
                return "Book added";
            }
            return "Role denied";
        }
        return "Invalid User";
    }

    @Override
    public Books removeBook(Long bookId) {
        return null;
    }

    @Override
    public String borrowBook(Long userId, Long bookId, String auth) {
        Users user = usersRepo.findByAuth(auth);
        if(user != null){
            if(Objects.equals(user.getRole(), "customer")){
                Books book = booksRepo.findByBookId(bookId);
                List<Users> usersList = book.getUsers();
                usersList.add(user);
                book.setUsers(usersList);
                booksRepo.save(book);
                return book.getName()+" borrowed by "+user.getName();
            }
            return "Admin can't borrow books";
        }
        return "Invalid User";
    }
}
