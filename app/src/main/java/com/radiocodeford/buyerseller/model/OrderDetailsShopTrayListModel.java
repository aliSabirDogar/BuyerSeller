package com.radiocodeford.buyerseller.model;

public class OrderDetailsShopTrayListModel {
    public String getProduct_details() {
        return product_details;
    }

    public void setName(String product_details) {
        this.product_details = product_details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    public OrderDetailsShopTrayListModel(String product_details, String price, String total) {
        this.product_details = product_details;
        this.price = price;
        this.total= total;
    }

    public String product_details,price,total;

}
