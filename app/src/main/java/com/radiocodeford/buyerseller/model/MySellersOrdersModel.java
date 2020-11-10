package com.radiocodeford.buyerseller.model;

public class MySellersOrdersModel {
    public void setnOrders(String nOrders) {
        this.nOrders = nOrders;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getnOrders() {
        return nOrders;
    }

    public String getProduct() {
        return product;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getClient() {
        return client;
    }

    public MySellersOrdersModel(String nOrders, String product, String price, String quantity, String client,String paymentTime) {
        this.nOrders = nOrders;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.client=client;
        this.paymentTime=paymentTime;
    }



    public String nOrders, product, price, quantity,client,paymentTime;

}
