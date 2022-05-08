package com.example.booktracker.models;

import com.example.booktracker.models.api.Book;

import java.util.List;

public class Books {
    private List<Book> books;

    public Books() {

    }

    public Books(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
