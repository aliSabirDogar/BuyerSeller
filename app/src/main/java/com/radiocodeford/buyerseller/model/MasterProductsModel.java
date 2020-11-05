package com.radiocodeford.buyerseller.model;

public class MasterProductsModel {
    String photo;
    String code;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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

    public MasterProductsModel(String photo, String code, String state, String product) {
        this.photo = photo;
        this.code = code;
        this.state = state;
        this.product = product;
    }

    String state;
    String product;
}
