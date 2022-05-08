package com.example.booktracker.models.api;

import java.io.Serializable;

public class ImageLinks implements Serializable {
    private String thumbnail;

    public ImageLinks() {}

    public ImageLinks(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
