package com.example.shop.ultil;

public class ProductALaCarte {
    private String urlImageBanner;
    private String foodName;
    private String foodPrice;
    private int portion;

    public ProductALaCarte(String urlImageBanner, String foodName, String foodPrice, int portion) {
        this.urlImageBanner = urlImageBanner;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.portion = portion;
    }

    public String getUrlImageBanner() {
        return urlImageBanner;
    }

    public void setUrlImageBanner(String urlImageBanner) {
        this.urlImageBanner = urlImageBanner;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }
}
