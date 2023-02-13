package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Query(value = "select b from Book b where b.user.id = :id",nativeQuery = true)
//    List<Book> findBooksByUserId (@Param("id") long id);
//
//@Query(value = "update book set user_id = :id where book_name=:title", nativeQuery = true)
//    Book loanBook(@Param("id")long id,@Param("title") String title);
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query(value = "select * from book where user_id=:id", nativeQuery = true)
    List<Book> findBooksByUserId (@Param("id") long id);

    @Query(value = "update  book set user_id=:id where book_name=:title",nativeQuery = true)
    Book loanBook(@Param("id")long id,@Param("title") String title);

}