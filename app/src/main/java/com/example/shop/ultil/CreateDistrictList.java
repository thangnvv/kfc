package com.example.shop.ultil;

import android.content.Context;

import com.example.shop.R;

public class CreateDistrictList {
    private static String[] districtList;

    public static String getDistrict(int position){
        return districtList[position];
    }

    public static String[] createDistrictList(Context context, int cityPosition) {

        switch (cityPosition) {
            case 1:
                districtList = context.getResources().getStringArray(R.array.districtInHoChiMinh);
                break;
            case 2:
                districtList = context.getResources().getStringArray(R.array.districtInHaNoi);
                break;
            case 3:
                districtList = context.getResources().getStringArray(R.array.districtInDaNang);
                break;
            case 4:
                districtList = context.getResources().getStringArray(R.array.districtInNhaTrang);
                break;
            case 5:
                districtList = context.getResources().getStringArray(R.array.districtInVungTau);
                break;
            default:
                districtList = null;
        }
        return districtList;
    }
}
