package com.example.shop.objects;


import java.util.ArrayList;

public class ProductALaCarte {
    private boolean upgradable;
    private ArrayList<Upgrade> upgrades;
    private String chosen_alacarte;
    private int chosen_upgrade_position;

    public ProductALaCarte(){

    }

    public String getChosen_alacarte() {
        return chosen_alacarte;
    }

    public void setChosen_alacarte(String chosen_alacarte) {
        this.chosen_alacarte = chosen_alacarte;
    }

    public int getChosen_upgrade_position() {
        return chosen_upgrade_position;
    }

    public void setChosen_upgrade_position(int chosen_upgrade_position) {
        this.chosen_upgrade_position = chosen_upgrade_position;
    }

    public boolean isUpgradable() {
        return upgradable;
    }

    public void setUpgradable(boolean upgradable) {
        this.upgradable = upgradable;
    }

    public ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(ArrayList<Upgrade> upgrades) {
        this.upgrades = upgrades;
    }
}
