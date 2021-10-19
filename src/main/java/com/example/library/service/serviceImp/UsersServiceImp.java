package com.example.library.service.serviceImp;

import com.example.library.model.Users;
import com.example.library.model.dto.UsersDto;
import com.example.library.repo.UsersRepo;
import com.example.library.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImp implements UsersService {
    @Autowired
    UsersRepo usersRepo;
    @Override
    public Users addUser(UsersDto usersDto) {
        Users user = new Users();
        user.setName(usersDto.getName());
        user.setPass(usersDto.getPass());
        return usersRepo.save(user);
    }

    @Override
    public Users removeUser(Long userId) {
        return null;
    }

    @Override
    public String login(UsersDto usersDto) {
        Users user = usersRepo.findByNameAndPass(usersDto.getName(), usersDto.getPass());
        if(user != null){
            return "Welcome "+user.getName();
        } else{
            return null;
        }
    }
}
