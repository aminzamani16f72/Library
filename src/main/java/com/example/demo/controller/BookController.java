package com.example.demo.controller;

import com.example.demo.dto.BookRequestModel;
import com.example.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<String> addNewBook(@RequestBody BookRequestModel bookRequestModel){
        bookService.addNewBook(bookRequestModel);
        return new ResponseEntity<>("new book is added", HttpStatus.OK);
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
}

