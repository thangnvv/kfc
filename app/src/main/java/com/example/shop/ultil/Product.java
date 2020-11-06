package com.example.shop.ultil;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private ArrayList<String> urls_banner;
    private String food_name;
    private String food_price;
    private String food_descript;
    private int portion;

    public Product(){

    }

    public Product(ArrayList<String> urls_banner, String food_name, String food_price, String food_descript, int portion) {
        this.urls_banner = urls_banner;
        this.food_name = food_name;
        this.food_price = food_price;
        this.food_descript = food_descript;
        this.portion = portion;
    }

    public ArrayList<String> getUrls_banner() {
        return urls_banner;
    }

    public void setUrls_banner(ArrayList<String> urls_banner) {
        this.urls_banner = urls_banner;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

    public String getFood_descript() {
        return food_descript;
    }

    public void setFood_descript(String food_descript) {
        this.food_descript = food_descript;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }
}
