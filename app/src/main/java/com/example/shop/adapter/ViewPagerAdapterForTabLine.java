package com.example.shop.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shop.fragment.ALaCarte_TabLayout_Fragment;
import com.example.shop.fragment.All_Line_Fragment;

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

        All_Line_Fragment _all_lineFragment = new All_Line_Fragment();

        switch (position){
            case 0:
                return _all_lineFragment;
            case 1:
                return _all_lineFragment;
            case 2:
                return _all_lineFragment;
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
