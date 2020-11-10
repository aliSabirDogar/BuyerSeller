package com.radiocodeford.buyerseller.model;

public class BuyerTrayModel {
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

    public BuyerTrayModel(String name, String cell, String shippingTime) {
        this.name = name;
        this.cell = cell;
        this.shippingTime = shippingTime;
    }

    String name,cell,shippingTime;
}
