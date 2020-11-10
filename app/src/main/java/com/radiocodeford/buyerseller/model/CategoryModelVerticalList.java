package com.radiocodeford.buyerseller.model;

public class CategoryModelVerticalList {
    int image;
    String name;

    public CategoryModelVerticalList(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public int getImage() {
        return this.image;
    }
}
