package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    Book findBookByTitle(String title);
//    @Query(value = "select * from book where user_id=:id", nativeQuery = true)
//    List<Book> findBooksByUserId (@Param("id") long id);
//
//    @Modifying
//    @Query(value = "update  book set user_id=:id where book_name=:title",nativeQuery = true)
//    void loanBook(@Param("id")long id,@Param("title") String title);




}