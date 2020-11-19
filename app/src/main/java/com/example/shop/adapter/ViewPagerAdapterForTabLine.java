package com.example.shop.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shop.fragment.ALaCarteTabLayoutFragment;
import com.example.shop.fragment.AllLineFragment;

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

        AllLineFragment all_lineFragment = new AllLineFragment();

        switch (position){
            case 0:
                return all_lineFragment;
            case 1:
                return all_lineFragment;
            case 2:
                return all_lineFragment;
            case 3:
                return new ALaCarteTabLayoutFragment();
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
