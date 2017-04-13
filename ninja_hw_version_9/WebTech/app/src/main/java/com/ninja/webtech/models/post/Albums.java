package com.ninja.webtech.models.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Albums {

    @SerializedName("posts")
    @Expose
    private Posts posts;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("picture")
    @Expose
    private Picture picture;
    @SerializedName("id")
    @Expose
    private String id;

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}