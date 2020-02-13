package com.poc.telstraproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonData {

    @SerializedName("title")
    private String title;

    @SerializedName("rows")
    private List<RowData> rowList;


    public String getTitle() {
        return title;
    }

    public List<RowData> getRowList() {
        return rowList;
    }
}
