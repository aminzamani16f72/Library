package com.example.demo.controller;

import com.example.demo.dto.BookRequestModel;
import com.example.demo.dto.BookResponseModel;
import com.example.demo.dto.UserRequestModel;
import com.example.demo.dto.UserResponseModel;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseModel>> findAllUsers(){

        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> addNewUser(@RequestBody UserRequestModel userRequestModel){
        userService.addNewUser(userRequestModel);
        return new ResponseEntity<>("user is created",HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserRequestModel userRequestModel,@PathVariable long id) {
        var response = userService.updateUser(id, userRequestModel);
        if (response.isPresent()) {
            userService.updateUser(id, userRequestModel);
            return new ResponseEntity<>("user is updated", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("the user is not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("the user is deleted",HttpStatus.OK);

    }

    @GetMapping("{id}/books")
    public ResponseEntity<List<BookResponseModel>> loanedBooks(@PathVariable long id){

        return new ResponseEntity<>(userService.loanedBooks(id),HttpStatus.OK);
    }
    @PostMapping("{id}/books")
    public ResponseEntity<String> loanedBooks(@PathVariable long id,@RequestBody BookRequestModel bookRequestModel){
        userService.addBookToUser(id, bookRequestModel);
        return new ResponseEntity<>("the book added to user",HttpStatus.OK);
    }
}