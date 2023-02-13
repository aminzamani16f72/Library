package com.example.demo.service;

import com.example.demo.dto.BookRequestModel;
import com.example.demo.dto.BookResponseModel;
import com.example.demo.dto.UserRequestModel;
import com.example.demo.dto.UserResponseModel;
import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<UserResponseModel> findAllUsers() {

        List<User> listOfUsers = userRepository.findAll();
        List<UserResponseModel> userResponseModelList = new ArrayList<>();
        for (var userList : listOfUsers) {
            UserResponseModel userResponseModel = new UserResponseModel(
                    userList.getId(), userList.getNationalId(), userList.getFirstName(),userList.getLastName());
            userResponseModelList.add(userResponseModel);
        }

        return userResponseModelList;
    }

    public Optional<UserResponseModel> findUserById(long id){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            User userPresent=user.get();
            return Optional.of(
                    new UserResponseModel(userPresent.getId(),userPresent.getNationalId(),userPresent.getFirstName(),userPresent.getLastName()));
        }
        else
            return Optional.empty();
    }

    public void addNewUser(UserRequestModel userRequestModel){

        User user=new User(userRequestModel.getId(),userRequestModel.getNationalId(),userRequestModel.getFirstName(),userRequestModel.getLastName());
        userRepository.save(user);

    }

    public Optional<Long> updateUser(long id, UserRequestModel userRequestModel) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            var userPresent = user.get();
            userPresent.setFirstName(userRequestModel.getFirstName());
            userPresent.setLastName(userRequestModel.getLastName());
            userPresent.setNationalId(userRequestModel.getNationalId());
            userRepository.save(userPresent);
            return Optional.of(userPresent.getId());
        } else {
            return Optional.empty();
        }
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

    public List<BookResponseModel> loanedBooks(long id){
        List<Book> bookList= bookRepository.findBooksByUserId(id);
        List<BookResponseModel> bookResponseModelList=new ArrayList<>();
        for (var bookItem:bookList){
            var bookModel=new BookResponseModel(bookItem.getId(),bookItem.getTitle());
            bookResponseModelList.add(bookModel);
        }
        return bookResponseModelList;
    }
    public void addBookToUser(long id, BookRequestModel bookRequestModel){
        bookRepository.loanBook(id,bookRequestModel.getTitle());
    }

}
