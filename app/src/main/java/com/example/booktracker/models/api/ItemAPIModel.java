package com.example.booktracker.models.api;

import java.io.Serializable;

public class ItemAPIModel implements Serializable {
    private String id;
    private VolumeInfoModel volumeInfo;

    public ItemAPIModel(String id, VolumeInfoModel volumeInfo) {
        this.id = id;
        this.volumeInfo = volumeInfo;
    }

    public VolumeInfoModel getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfoModel volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
