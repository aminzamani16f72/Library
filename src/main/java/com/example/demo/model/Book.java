package com.example.demo.model;

import jakarta.persistence.*;
@Table(name = "book")
@Entity
public class Book {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "book_name")
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Book(long id, String title, User user) {
        this.id = id;
        this.title = title;
        this.user = user;
    }

    public Book(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

