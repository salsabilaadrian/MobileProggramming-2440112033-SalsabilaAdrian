package com.mobprog.uas.model;

import com.google.gson.annotations.SerializedName;

public class ModelProdComp {

    @SerializedName("id")
    private int id;

    @SerializedName("logo_path")
    private String logo;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
