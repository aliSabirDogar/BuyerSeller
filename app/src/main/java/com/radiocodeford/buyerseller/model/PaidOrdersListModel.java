package com.radiocodeford.buyerseller.model;

public class PaidOrdersListModel {
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public PaidOrdersListModel(String orderCode, String cellPhone, String name, String time) {
        this.orderCode = orderCode;
        this.cellPhone = cellPhone;
        this.name = name;
        this.time = time;
    }



    public String orderCode, cellPhone, name, time;

}

