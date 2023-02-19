package com.example.demo.controller;

import com.example.demo.dto.BookRequestModel;
import com.example.demo.dto.BookResponseModel;
import com.example.demo.service.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponseModel>> listOfAllBooks(){
        return new ResponseEntity<>(bookService.listOfBooks(),HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Optional<BookResponseModel>> findBookById(@PathVariable long id){
        var optionalBook=bookService.findBookById(id);
        if(optionalBook.isPresent()){
            return new ResponseEntity<>(Optional.of(optionalBook.get()),HttpStatus.OK);
        }
            return new ResponseEntity<>(Optional.empty(),HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<String> addNewBook(@RequestBody BookRequestModel bookRequestModel){
        return new ResponseEntity<>(bookService.addNewBook(bookRequestModel), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateBookInfo(@RequestBody BookRequestModel bookRequestModel,@PathVariable long id){
        var response=bookService.updateBookInfo(bookRequestModel, id);
        if(response.isPresent()){
            bookService.updateBookInfo(bookRequestModel, id);
            return new ResponseEntity<>("the book info is updated",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("the book not founded",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable long id){
        bookService.deleteBookById(id);
        return new ResponseEntity<>(bookService.deleteBookById(id),HttpStatus.OK);
    }
}

