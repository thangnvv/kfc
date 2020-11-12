package com.example.shop.ultil;

public class Upgrade {
    private String product;
    private String price_change;
    private String category;
    private int portion;
    public Upgrade(){
    }

    public Upgrade(String product, String price_change, String category) {
        this.product = product;
        this.price_change = price_change;
        this.category = category;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice_change() {
        return price_change;
    }

    public void setPrice_change(String price_change) {
        this.price_change = price_change;
    }
}
