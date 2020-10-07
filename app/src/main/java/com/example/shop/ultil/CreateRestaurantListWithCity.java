package com.example.shop.ultil;

import android.content.Context;

import com.example.shop.R;

import java.util.ArrayList;

public class CreateRestaurantListWithCity {
    public static ArrayList<Restaurant> createRestaurantList(Context context , int position){
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
}
