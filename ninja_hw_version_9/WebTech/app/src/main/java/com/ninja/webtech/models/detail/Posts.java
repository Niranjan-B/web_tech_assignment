package com.ninja.webtech.models.detail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Posts {

    @SerializedName("data")
    @Expose
    private List<Datum__> data = null;
    @SerializedName("paging")
    @Expose
    private Paging__ paging;

    public List<Datum__> getData() {
        return data;
    }

    public void setData(List<Datum__> data) {
        this.data = data;
    }

    public Paging__ getPaging() {
        return paging;
    }

    public void setPaging(Paging__ paging) {
        this.paging = paging;
    }

}