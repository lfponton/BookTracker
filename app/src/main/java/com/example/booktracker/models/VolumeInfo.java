package com.example.booktracker.models;

import java.io.Serializable;
import java.util.List;

public class VolumeInfo implements Serializable {
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private List<Isbn> industryIdentifiers;
    private int pageCount;
    private List<String> categories;
    private double averageRating;
    private ImageLinks imageLinks;

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
            return authors;
    }

    public VolumeInfo() {}

    public VolumeInfo(String title, List<String> authors, String publisher,
                      String publishedDate, String description,
                      List<Isbn> industryIdentifiers, int pageCount,
                      List<String> categories, double averageRating,
                      ImageLinks imageLinks) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.industryIdentifiers = industryIdentifiers;
        this.pageCount = pageCount;
        this.categories = categories;
        this.averageRating = averageRating;
        this.imageLinks = imageLinks;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Isbn> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<Isbn> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }
}
