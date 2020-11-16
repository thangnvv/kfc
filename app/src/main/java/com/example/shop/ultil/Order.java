package com.example.shop.ultil;

import java.util.ArrayList;

public class Order {
    private String total;
    private ArrayList<Product> listProduct;
    private String date;
    private String shippingFee;

    public Order(){}

    public Order(String total, ArrayList<Product> listProduct, String date, String shippingFee) {
        this.total = total;
        this.listProduct = listProduct;
        this.date = date;
        this.shippingFee = shippingFee;
    }

    public String getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(String shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
