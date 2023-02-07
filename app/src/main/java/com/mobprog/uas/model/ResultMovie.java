package com.mobprog.uas.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultMovie {

    @SerializedName("results")
    private List<ModelMovie> results;

    public List<ModelMovie> getResults() {
        return results;
    }

    public void setResults(List<ModelMovie> results) {
        this.results = results;
    }
}
