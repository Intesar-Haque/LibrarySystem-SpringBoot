package com.example.library.model;

import com.example.library.service.BooksService;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Books {
    @ManyToMany( fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "borrowed_books",
            joinColumns = { @JoinColumn(name = "bookId") },
            inverseJoinColumns = { @JoinColumn(name = "userId") }
    )
    List<Users> users = new ArrayList<>();
    @Id
    @GeneratedValue
    Long bookId;
    @NotNull
    String name;
    String year;
    String author;
    String genre;

}
