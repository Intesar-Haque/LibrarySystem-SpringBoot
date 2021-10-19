package com.example.library.service.serviceImp;

import com.example.library.model.Users;
import com.example.library.model.dto.UsersDto;
import com.example.library.repo.UsersRepo;
import com.example.library.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UsersServiceImp implements UsersService {
    @Autowired
    UsersRepo usersRepo;
    @Override
    public Users addUser(UsersDto usersDto) {
        Users user = new Users();
        user.setName(usersDto.getName());
        user.setPass(usersDto.getPass());
        String auth = generateRandom(usersDto.getName());
        user.setAuth(auth);
        return usersRepo.save(user);
    }

    @Override
    public Users removeUser(Long userId) {
        return null;
    }

    @Override
    public String login(UsersDto usersDto, String auth) {
        Users user = usersRepo.findByNameAndPass(usersDto.getName(), usersDto.getPass());
        if(user != null){
            if(Objects.equals(user.getAuth(), auth)){
                auth = generateRandom(user.getName());
                user.setAuth(auth);
                usersRepo.save(user);
                return "Welcome "+user.getName()+", New Auth id : "+auth;
            } else{
                return "Login failed";
            }
        } else{
            return null;
        }

    }

    @Override
    public String generateRandom(String username) {
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
