package com.example.library.repo;

import com.example.library.model.Books;
import com.example.library.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, String> {
//    List<Users> findByNameAndContact(String name, String  contact);
//
    Users findByUserId(Long id);
    Users findByNameAndPass(String name, String pass);

//    @Transactional
//    @Modifying
//    void deleteById(Long id);

}
