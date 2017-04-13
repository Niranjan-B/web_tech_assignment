package com.ninja.webtech.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paging {

    @SerializedName("next")
    @Expose
    private String next;

    @SerializedName("previous")
    @Expose
    private String previous;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

}