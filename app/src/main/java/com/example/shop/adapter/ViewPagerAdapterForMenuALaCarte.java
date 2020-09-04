package com.example.shop.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shop.fragment.ALaCarte_Fragment;
import com.example.shop.fragment.Fried_RoastedChicken_Fragment;
import com.example.shop.fragment.HotDeals_Fragment;
import com.example.shop.fragment.Rice_Burger_Fragment;

public class ViewPagerAdapterForMenuALaCarte extends FragmentPagerAdapter{

    private FragmentManager fragmentManager;
    private int numOfTabs;

    public ViewPagerAdapterForMenuALaCarte(FragmentManager fragmentManager, int numOfTabs){
        super(fragmentManager, numOfTabs);
        this.fragmentManager = fragmentManager;
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new Fried_RoastedChicken_Fragment();
            case 1:
                return new Rice_Burger_Fragment();
            case 2:
                return new HotDeals_Fragment();
            case 3:
                return new ALaCarte_Fragment();
            default:
                Log.d("DDD",  "Check tab 1");
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
