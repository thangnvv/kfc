package com.example.shop.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shop.fragment.Fragment_ALaCarte_Menu;
import com.example.shop.fragment.Fragment_ALaCarte_TabLayout;
import com.example.shop.fragment.ForOne_Fragment_Old;
import com.example.shop.fragment.ForSharing_Fragment;
import com.example.shop.fragment.HotDeals_Fragment;

public class ViewPagerAdapterForMainTab extends FragmentPagerAdapter {

    private FragmentManager fragmentManager;
    private int numOfTabs;

    public ViewPagerAdapterForMainTab(FragmentManager fragmentManager, int numOfTabs){
        super(fragmentManager, numOfTabs);
        this.fragmentManager = fragmentManager;
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new ForOne_Fragment_Old();
            case 1:
                return new ForSharing_Fragment();
            case 2:
                return new HotDeals_Fragment();
            case 3:
                return new Fragment_ALaCarte_Menu();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
