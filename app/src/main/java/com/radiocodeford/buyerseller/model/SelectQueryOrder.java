package com.radiocodeford.buyerseller.model;

public class SelectQueryOrder {
    public SelectQueryOrder(String code, String state, String product, String quantity,String unit) {
        this.code = code;
        this.state = state;
        this.product = product;
        this.quantity = quantity;
        this.unit=unit;
    }

    String code;
    String state;
    String product;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    String unit;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    String quantity;
}
