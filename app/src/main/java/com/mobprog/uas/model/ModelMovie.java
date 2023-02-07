package com.mobprog.uas.model;

import com.google.gson.annotations.SerializedName;

public class ModelMovie {

    @SerializedName("id")
    private int id;

    @SerializedName("backdrop_path")
    private String url;

    @SerializedName("title")
    private String nama;

    @SerializedName("overview")
    private String desc;

    @SerializedName("popularity")
    private double popularity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
