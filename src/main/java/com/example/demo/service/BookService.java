package com.example.demo.service;

import com.example.demo.dto.BookRequestModel;
import com.example.demo.dto.BookResponseModel;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponseModel> listOfBooks() {
        List<Book> bookList = bookRepository.findAll();
       return bookList.stream().map(n ->new BookResponseModel(n.getId(),n.getTitle())).toList();
    }

    public Optional<BookResponseModel> findBookById(long id) {
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            var bookPresent = book.get();
            return Optional.of(new BookResponseModel(bookPresent.getId(), bookPresent.getTitle()));
        } else
            return Optional.empty();
    }

    public String addNewBook(BookRequestModel bookRequestModel) {
        var book = new Book(bookRequestModel.getTitle());
        bookRepository.save(book);

        return "the book’s identity = " + " " + book.getId();
    }

    public Optional<Long> updateBookInfo(BookRequestModel bookRequestModel, long id) {
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            var bookPresent = book.get();
            bookPresent.setTitle(bookRequestModel.getTitle());
            bookRepository.save(bookPresent);
            return Optional.of(bookPresent.getId());
        } else
            return Optional.empty();
    }

    public String deleteBookById(long id) {
        bookRepository.deleteById(id);
        return "the book with id  " + id + "is deleted";
    }
}
