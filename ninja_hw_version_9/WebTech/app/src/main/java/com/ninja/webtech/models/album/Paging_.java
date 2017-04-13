package com.ninja.webtech.models.album;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paging_ {

    @SerializedName("cursors")
    @Expose
    private Cursors_ cursors;
    @SerializedName("next")
    @Expose
    private String next;

    public Cursors_ getCursors() {
        return cursors;
    }

    public void setCursors(Cursors_ cursors) {
        this.cursors = cursors;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}