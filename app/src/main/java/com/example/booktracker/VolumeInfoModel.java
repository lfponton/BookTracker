package com.example.booktracker;

import java.util.List;

public class VolumeInfoModel {
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private List<IsbnAPIModel> industryIdentifiers;
    private int pageCount;
    private List<String> categories;
    private double averageRating;
    private ImageLinksAPIModel imageLinks;
}
