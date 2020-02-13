package com.poc.telstraproject.model;

public class RowData {
    private String title;
    private String description;
    private String imageHref;

    public RowData(String title, String description, String imageHref) {
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }
}
