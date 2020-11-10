package com.radiocodeford.buyerseller.model;

public class ShopTrayModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime;
    }

    String name;
    String cell;

    public ShopTrayModel(String name, String cell, String shippingTime) {
        this.name = name;
        this.cell = cell;
        this.shippingTime = shippingTime;
    }

    String shippingTime;
}
