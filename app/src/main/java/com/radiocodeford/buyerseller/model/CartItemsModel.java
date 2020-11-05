package com.radiocodeford.buyerseller.model;

public class CartItemsModel {
    public CartItemsModel(String price, String quantity, String product, String seller, String subtotal, int cart_item_image) {
        this.price = price;
        this.quantity = quantity;
        this.product = product;
        this.seller = seller;
        this.subtotal = subtotal;
        this.cart_item_image = cart_item_image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public int getCart_item_image() {
        return cart_item_image;
    }

    public void setCart_item_image(int cart_item_image) {
        this.cart_item_image = cart_item_image;
    }

    String price,quantity,product,seller,subtotal;
    int cart_item_image;
}
