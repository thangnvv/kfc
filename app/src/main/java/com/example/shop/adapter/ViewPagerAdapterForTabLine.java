package com.example.shop.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shop.fragment.Fragment_ALaCarte_TabLayout;
import com.example.shop.fragment.Fragment_All_Line;

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

        Fragment_All_Line fragment_all_line = new Fragment_All_Line();

        switch (position){
            case 0:
                return fragment_all_line;
            case 1:
                return fragment_all_line;
            case 2:
                return fragment_all_line;
            case 3:
                return new Fragment_ALaCarte_TabLayout();
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
