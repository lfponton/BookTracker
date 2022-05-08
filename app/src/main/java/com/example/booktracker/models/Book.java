package com.example.booktracker.models;


public class Book {

    private int id;
    private String title;
    private String author;
    private int pages;
    private String type; // TODO: should be an enum
    private int iconId;

    public Book() {}

    public Book(String title, String author, int pages, String type, int iconId) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.type = type;
        this.iconId = iconId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
