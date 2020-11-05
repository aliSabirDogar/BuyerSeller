package com.radiocodeford.buyerseller.model;

public class ModelOrderReview {
    String product_name;
    String quantity;



    public ModelOrderReview(String product_name, String quantity) {
        this.product_name = product_name;
        this.quantity = quantity;
        }

    public String getProduct_name() {
        return this.product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
