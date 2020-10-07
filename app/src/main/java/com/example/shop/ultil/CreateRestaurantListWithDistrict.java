package com.example.shop.ultil;

import java.util.ArrayList;

public class CreateRestaurantListWithDistrict {

    public static ArrayList<Restaurant> createRestaurantListWithDistrict(ArrayList<Restaurant> resArrList, String district  ){
        ArrayList<Restaurant> arrList = new ArrayList<>();
        int districtLength = district.length();
        for(int i = 0; i < resArrList.size(); i++){
            for(int j = 0; j <= resArrList.get(i).getRestaurantAddress().length() - districtLength; j++ ){
                if(district.equals(resArrList.get(i).getRestaurantAddress().substring(j, j+districtLength))){
                    arrList.add(resArrList.get(i));
                }
            }
        }
        return arrList;
    }

}
