package com.example.library.repo;

import com.example.library.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BooksRepo extends JpaRepository<Books, String> {
//    List<Books> findByNameAndAuthor(String name, String  author);
//
    Books findByBookId(Long id);
//
//    @Transactional
//    @Modifying
//    void deleteById(Long id);

}
