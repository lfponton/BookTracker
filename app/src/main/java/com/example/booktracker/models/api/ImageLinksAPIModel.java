package com.example.booktracker.models.api;

import java.io.Serializable;

public class ImageLinksAPIModel implements Serializable {
    private String thumbnail;
    private String medium;
    private String large;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public ImageLinksAPIModel(String thumbnail, String medium, String large) {
        this.thumbnail = thumbnail;
        this.medium = medium;
        this.large = large;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
