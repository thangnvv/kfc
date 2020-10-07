package com.example.shop.ultil;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private ArrayList<BannerImage> urlImageBanner;
    private String foodName;
    private String foodPrice;
    private String foodDescrip;
    private int portion;

    public Product(ArrayList<BannerImage> urlImageBanner, String foodName, String foodPrice, String foodDescrip, int portion) {
        this.urlImageBanner = urlImageBanner;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodDescrip = foodDescrip;
        this.portion = portion;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public List<BannerImage> getUrlImageBanner() {
        return urlImageBanner;
    }

    public void setUrlImageBanner(ArrayList<BannerImage> urlImageBanner) {
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

    public String getFoodDescrip() {
        return foodDescrip;
    }

    public void setFoodDescrip(String foodDescrip) {
        this.foodDescrip = foodDescrip;
    }
}
