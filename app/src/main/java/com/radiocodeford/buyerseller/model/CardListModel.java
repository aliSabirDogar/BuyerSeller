package com.radiocodeford.buyerseller.model;

public class CardListModel {
    int id_;
    int image;
    String name;
    String version;

    public CardListModel(String name, String version, int id_, int image) {
        this.name = name;
        this.version = version;
        this.id_ = id_;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

    public int getImage() {
        return this.image;
    }

    public int getId() {
        return this.id_;
    }
}
