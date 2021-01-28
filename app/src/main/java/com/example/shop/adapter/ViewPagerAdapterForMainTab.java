package com.example.shop.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shop.fragment.ALaCarteMenuFragment;
import com.example.shop.fragment.ForOneFragment;
import com.example.shop.fragment.ForSharingFragment;
import com.example.shop.fragment.HotDealsFragment;

public class ViewPagerAdapterForMainTab extends FragmentPagerAdapter {

    private FragmentManager fragmentManager;
    private int numOfTabs;
    private Context context;

    public ViewPagerAdapterForMainTab(FragmentManager fragmentManager, int numOfTabs, Context context){
        super(fragmentManager, numOfTabs);
        this.fragmentManager = fragmentManager;
        this.numOfTabs = numOfTabs;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 1:
                return new ForSharingFragment(context);
            case 2:
                return new HotDealsFragment(context);
            case 3:
                return new ALaCarteMenuFragment(context);
            default:
                return new ForOneFragment(context);
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
