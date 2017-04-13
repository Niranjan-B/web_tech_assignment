package com.ninja.webtech.models.album;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Albums {

    @SerializedName("albums")
    @Expose
    private Albums_ albums;
    @SerializedName("id")
    @Expose
    private String id;

    public Albums_ getAlbums() {
        return albums;
    }

    public void setAlbums(Albums_ albums) {
        this.albums = albums;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}