package com.mobprog.uas.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelDetailMovie {

    @SerializedName("backdrop_path")
    private String url;

    @SerializedName("budget")
    private int budget;

    @SerializedName("original_title")
    private String name;

    @SerializedName("overview")
    private String desc;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("release_date")
    private String release;

    @SerializedName("revenue")
    private int revenue;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("genres")
    private List<ModelListGenre> genres;

    @SerializedName("production_companies")
    private List<ModelProdComp> prodCompanies;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<ModelListGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<ModelListGenre> genres) {
        this.genres = genres;
    }

    public List<ModelProdComp> getProdCompanies() {
        return prodCompanies;
    }

    public void setProdCompanies(List<ModelProdComp> prodCompanies) {
        this.prodCompanies = prodCompanies;
    }
}
