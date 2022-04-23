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

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        if (authors != null) {
            return authors.get(0);
        }
        else
            return "UNKNOWN";

    }

    public ImageLinksAPIModel getImageLinks() {
        return imageLinks;
    }
}
