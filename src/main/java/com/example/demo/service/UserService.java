package com.example.demo.service;

import com.example.demo.dto.BookRequestModel;
import com.example.demo.dto.BookResponseModel;
import com.example.demo.dto.UserRequestModel;
import com.example.demo.dto.UserResponseModel;
import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Repository
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<UserResponseModel> findAllUsers() {
        List<User> listOfUsers = userRepository.findAll();
        return listOfUsers.stream().map(n -> new UserResponseModel(n.getId(), n.getNationalId(), n.getFirstName(), n.getLastName())).toList();
    }

    public Optional<UserResponseModel> findUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userPresent -> new UserResponseModel(userPresent.getId(), userPresent.getNationalId(), userPresent.getFirstName(), userPresent.getLastName()));
    }

    public void addNewUser(UserRequestModel userRequestModel) {

        var user = new User(userRequestModel.getNationalId(), userRequestModel.getFirstName(), userRequestModel.getLastName());
        userRepository.save(user);

    }

    public Optional<Long> updateUser(long id, UserRequestModel userRequestModel) {
        var userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            user.setFirstName(userRequestModel.getFirstName());
            user.setLastName(userRequestModel.getLastName());
            user.setNationalId(userRequestModel.getNationalId());
            userRepository.save(user);
            return Optional.of(user.getId());
        } else {
            return Optional.empty();
        }
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }


    public Optional<Long> addBookToUser(long id, BookRequestModel bookRequestModel) {
        List<Book> bookList;
        var userOptional = userRepository.findById(id);
        var book = bookRepository.findBookByTitle(bookRequestModel.getTitle());
        if(userOptional.isPresent()){
           var user=userOptional.get();
           bookList=user.getBookList();
           bookList.add(book);
           user.setBookList(bookList);
           book.setUser(user);
           userRepository.save(user);
           bookRepository.save(book);
           return Optional.of(user.getId());
        }
        return Optional.empty();
    }



//        var book=bookRepository.findBookByTitle(bookRequestModel.getTitle());
//        book.setUser();
//
//        var optionalUser=userRepository.findById(id);
//        if(optionalUser.isPresent()){
//            var user=optionalUser.get();
//            List<Book> bookList=user.getBookList();
//            bookList.add(book);
//            user.setBookList(bookList);
//            userRepository.save(user);


//    public List<BookResponseModel> loanedBooks(long id) {
//        List<Book> bookList = bookRepository.findBooksByUserId(id);
//       List<BookResponseModel> bookResponseModelList = new ArrayList<>();
//        for (var bookItem : bookList) {
//           var bookModel = new BookResponseModel(bookItem.getId(), bookItem.getTitle());
//           bookResponseModelList.add(bookModel);
//       }
//       return bookResponseModelList;
//    }
//
//    @Transactional
//    public void addBookToUser(long id, BookRequestModel bookRequestModel) {
//        bookRepository.loanBook(id, bookRequestModel.getTitle());
//    }

}
