package com.example.shop.utils;

import android.content.Context;

import com.example.shop.R;
import com.example.shop.utils.objects.Restaurant;

import java.util.ArrayList;

public class CreateRestaurantListHelper {

    public static ArrayList<Restaurant> createRestaurantListInCity(Context context , int position){
        String[] resName;
        String[] resAddress;
        ArrayList<Restaurant> mArrListRes = new ArrayList<>();
        switch (position){
            case 1:
                resName     = context.getResources().getStringArray(R.array.restaurantNameInHoChiMinh);
                resAddress  = context.getResources().getStringArray(R.array.restaurantAddressInHoChiMinh);
                break;
            case 2:
                resName     = context.getResources().getStringArray(R.array.restaurantNameInHaNoi);
                resAddress  = context.getResources().getStringArray(R.array.restaurantAddressInHaNoi);
                break;
            case 3:
                resName     = context.getResources().getStringArray(R.array.restaurantNameInDaNang);
                resAddress  = context.getResources().getStringArray(R.array.restaurantAddressInDaNang);
                break;
            case 4:
                resName     = context.getResources().getStringArray(R.array.restaurantNameInKhanhHoa);
                resAddress  = context.getResources().getStringArray(R.array.restaurantAddressInKhanhHoa);
                break;
            case 5:
                resName     = context.getResources().getStringArray(R.array.restaurantNameInVungTau);
                resAddress  = context.getResources().getStringArray(R.array.restaurantAddressInVungtau);
                break;
            default:
                resName     = null;
                resAddress  = null;
                break;
        }
        for(int i = 0 ; i< resName.length ; i++){
            mArrListRes.add(new Restaurant(resName[i], resAddress[i]));
        }
        return mArrListRes;
    }

    public static ArrayList<Restaurant> createRestaurantListInDistrict(ArrayList<Restaurant> resArrList, String district  ){
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
