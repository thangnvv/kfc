package com.example.shop.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shop.fragment.ALaCarte_TabLayout_Fragment;
import com.example.shop.fragment.Fragment_Group_Combo;
import com.example.shop.fragment.Fragment_HotDeals;
import com.example.shop.fragment.Fragment_One_Person_Combo;

public class ViewPagerAdapterForTabLine extends FragmentPagerAdapter {

    private FragmentManager fragmentManager;
    private int numOfTabs;

    public ViewPagerAdapterForTabLine(FragmentManager fragmentManager, int numOfTabs){
        super(fragmentManager, numOfTabs);
        this.fragmentManager = fragmentManager;
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_One_Person_Combo();
            case 1:
                return new Fragment_Group_Combo();
            case 2:
                return new Fragment_HotDeals();
            case 3:
                return new ALaCarte_TabLayout_Fragment();
            default:
                Log.d("DDD",  "Check tab");
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
