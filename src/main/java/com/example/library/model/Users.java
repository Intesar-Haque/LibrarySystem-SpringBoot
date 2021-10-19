package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Users {
    @JsonIgnore
    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "users")
    private List<Books> books = new ArrayList<>();
    @Id
    @GeneratedValue
    Long userId;
    @NotNull
    String name;
    String pass;

}
