package com.example.library.service.serviceImp;

import com.example.library.model.Books;
import com.example.library.model.dto.BooksDto;
import com.example.library.service.BooksService;
import org.springframework.stereotype.Service;

@Service
public class BooksServiceImp implements BooksService {
    @Override
    public Books addBook(BooksDto booksDto) {
        return null;
    }

    @Override
    public Books removeBook(Long bookId) {
        return null;
    }
}
