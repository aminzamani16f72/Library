package com.example.demo.dto;

public class BookResponseModel {

    private long id;
    private String title;

    public BookResponseModel(long id, String title) {
        this.id=id;
        this.title=title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
