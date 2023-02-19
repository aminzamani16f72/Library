package com.example.demo.dto;

public class UserRequestModel {


    private final long nationalId;
    private final String firstName;
    private final String lastName;

    public UserRequestModel(long id,long nationalId, String firstName, String lastName) {
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public long getNationalId() {
        return nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
