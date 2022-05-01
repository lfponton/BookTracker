package com.example.booktracker.models.api;

import java.io.Serializable;

public class ImageLinksAPIModel implements Serializable {
    private String thumbnail;

    public ImageLinksAPIModel(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
