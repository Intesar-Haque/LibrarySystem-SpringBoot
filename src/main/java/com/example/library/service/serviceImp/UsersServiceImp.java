package com.example.library.service.serviceImp;

import com.example.library.model.Books;
import com.example.library.model.Users;
import com.example.library.model.UsersRole;
import com.example.library.model.dto.UsersDto;
import com.example.library.repo.BooksRepo;
import com.example.library.repo.UsersRepo;
import com.example.library.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@AllArgsConstructor
public class UsersServiceImp implements UsersService, UserDetailsService {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    BooksRepo booksRepo;

    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<String> addUser(UsersDto usersDto) {
        boolean userExists = usersRepo.findByUname(usersDto.getUname()).isPresent();
        if(userExists){
            return new ResponseEntity<>("Username already taken", HttpStatus.IM_USED);
        } else{
            Users user = new Users();
            user.setName(usersDto.getName());
            user.setUname(usersDto.getUname());
            user.setUsersRole(UsersRole.USER);
            String encodedPassword = passwordEncoder.encode(usersDto.getPass());
            user.setPass(encodedPassword);
            usersRepo.save(user);
            return  new ResponseEntity<>("User Registered", HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<String>  removeUser(Long userId) {
        Users users = usersRepo.findByUserId(userId);
        if(users != null) {
            for (Books books : users.getBooks()) {
                books.getUsers().remove(users);
                users.getBooks().removeAll(users.getBooks());
                usersRepo.save(users);
                usersRepo.deleteByUserId(userId);
                return new ResponseEntity<>("User Removed", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public List<Books> borrowed(String uname) {
        Users user = usersRepo.findByUname(uname).orElseThrow(null);
        if(Objects.nonNull(user)){
            return user.getBooks();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {
        return usersRepo.findByUname(uname).orElseThrow( () -> new UsernameNotFoundException("User doesn't Exist"));
    }
}
