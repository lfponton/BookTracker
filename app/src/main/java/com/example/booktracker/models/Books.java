package com.example.booktracker.models;

import com.example.booktracker.models.api.ItemAPIModel;

import java.util.List;

public class Books {
    private List<ItemAPIModel> books;

    public Books() {

    }

    public Books(List<ItemAPIModel> books) {
        this.books = books;
    }

    public List<ItemAPIModel> getBooks() {
        return books;
    }

    public void setBooks(List<ItemAPIModel> books) {
        this.books = books;
    }
}
