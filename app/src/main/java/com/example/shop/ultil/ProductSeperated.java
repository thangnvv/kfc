package com.example.shop.ultil;

public class ProductSeperated {
    String choosenName;
    String[] options;

    public ProductSeperated(String choosenName, String[] options) {
        this.choosenName = choosenName;
        this.options = options;
    }

    public String getChosenName() {
        return choosenName;
    }

    public void setChoosenName(String choosenName) {
        this.choosenName = choosenName;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
