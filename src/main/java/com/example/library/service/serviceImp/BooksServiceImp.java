package com.example.library.service.serviceImp;

import com.example.library.model.Books;
import com.example.library.model.dto.BooksDto;
import com.example.library.repo.BooksRepo;
import com.example.library.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksServiceImp implements BooksService {
    @Autowired
    BooksRepo booksRepo;
    @Override
    public Books addBook(BooksDto booksDto) {
        Books books = new Books();
        books.setName(booksDto.getName());
        books.setYear(booksDto.getYear());
        books.setAuthor(booksDto.getAuthor());
        books.setGenre(booksDto.getGenre());
        return booksRepo.save(books);
    }

    @Override
    public Books removeBook(Long bookId) {
        return null;
    }
}
