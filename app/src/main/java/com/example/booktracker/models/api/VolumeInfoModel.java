package com.example.booktracker.models.api;

import java.io.Serializable;
import java.util.List;

public class VolumeInfoModel implements Serializable {
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

    public VolumeInfoModel(String title, List<String> authors, String publisher,
                           String publishedDate, String description,
                           List<IsbnAPIModel> industryIdentifiers, int pageCount,
                           List<String> categories, double averageRating,
                           ImageLinksAPIModel imageLinks) {
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

    public ImageLinksAPIModel getImageLinks() {
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

    public List<IsbnAPIModel> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<IsbnAPIModel> industryIdentifiers) {
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

    public void setImageLinks(ImageLinksAPIModel imageLinks) {
        this.imageLinks = imageLinks;
    }
}
