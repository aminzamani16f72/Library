package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "national_Code")
    private long nationalId;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Book> bookList;

    public User() {
    }

    public User(long id, long nationalId, String firstName, String lastName) {
        this.id = id;
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(long id, long nationalId, String firstName, String lastName, List<Book> bookList) {
        this.id = id;
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookList = bookList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNationalId() {
        return nationalId;
    }

    public void setNationalId(long nationalId) {
        this.nationalId = nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
