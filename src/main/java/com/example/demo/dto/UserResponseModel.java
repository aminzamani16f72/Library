package com.example.demo.dto;

public class UserResponseModel {
    private final long id;
    private final long nationalId;
    private final String firstName;
    private final String lastName;

    public UserResponseModel(long id, long nationalId, String firstName, String lastName) {
        this.id = id;
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
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
