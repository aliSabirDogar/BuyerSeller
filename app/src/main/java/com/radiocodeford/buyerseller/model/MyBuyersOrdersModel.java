package com.radiocodeford.buyerseller.model;

public class MyBuyersOrdersModel {


    public void setBuyersnOrders(String buyersnOrders) {
        this.buyersnOrders = buyersnOrders;
    }

    public void setBuyersproduct(String buyersproduct) {
        this.buyersproduct = buyersproduct;
    }

    public void setBuyersprice(String buyersprice) {
        this.buyersprice = buyersprice;
    }

    public void setBuyersquantity(String buyersquantity) {
        this.buyersquantity = buyersquantity;
    }

    public void setBuyersclient(String buyersclient) {
        this.buyersclient = buyersclient;
    }

    public void setBuyerspaymentTime(String buyerspaymentTime) {
        this.buyerspaymentTime = buyerspaymentTime;
    }

    public String getBuyersnOrders() {
        return buyersnOrders;
    }

    public String getBuyersproduct() {
        return buyersproduct;
    }

    public String getBuyersprice() {
        return buyersprice;
    }

    public String getBuyersquantity() {
        return buyersquantity;
    }

    public String getBuyersclient() {
        return buyersclient;
    }

    public String getBuyerspaymentTime() {
        return buyerspaymentTime;
    }

    public MyBuyersOrdersModel(String buyersnOrders, String buyersproduct, String buyersprice, String buyersquantity, String buyersclient, String buyerspaymentTime) {
        this.buyersnOrders = buyersnOrders;
        this.buyersproduct = buyersproduct;
        this.buyersprice = buyersprice;
        this.buyersquantity = buyersquantity;
        this.buyersclient=buyersclient;
        this.buyerspaymentTime=buyerspaymentTime;
    }



    public String buyersnOrders, buyersproduct, buyersprice, buyersquantity,buyersclient,buyerspaymentTime;

}
