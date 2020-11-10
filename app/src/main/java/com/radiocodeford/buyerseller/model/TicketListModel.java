package com.radiocodeford.buyerseller.model;

public class TicketListModel {


    public TicketListModel(String quantity, String description, String price, String amount) {
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }


    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getAmount() {
        return amount;
    }

    public String quantity, description, price, amount;

}


