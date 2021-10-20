package com.example.library.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {
    public UsersDto(String name, String pass, String uname) {
        this.name = name;
        this.pass = pass;
        this.uname = uname;
    }


    String name;
    String pass;
    String uname;
}
