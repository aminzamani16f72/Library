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
        List<BookResponseModel> bookResponseModelList = new ArrayList<>();
        for (var bookItem : bookList) {
            var bookResponseModel = new BookResponseModel(bookItem.getId(), bookItem.getTitle());
            bookResponseModelList.add(bookResponseModel);
        }
        return bookResponseModelList;
    }

    public Optional<BookResponseModel> findBookById(long id) {
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            var bookPresent = book.get();
            return Optional.of(new BookResponseModel(bookPresent.getId(), bookPresent.getTitle()));
        } else
            return Optional.empty();
    }

    public void addNewBook(BookRequestModel bookRequestModel) {
        var book = new Book(bookRequestModel.getId(), bookRequestModel.getTitle());
        bookRepository.save(book);
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

    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }
}
