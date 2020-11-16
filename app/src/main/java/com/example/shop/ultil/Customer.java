package com.example.shop.ultil;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String full_name, email, password, phone_number, uid;
    private ArrayList<String> order_history;

    public Customer(){}

    public Customer(String uid, String full_name, String email, String password, String phone_number) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.uid = uid;
    }

    public ArrayList<String> getOrder_history() {
        return order_history;
    }

    public void setOrder_history(ArrayList<String> order_history) {
        this.order_history = order_history;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
