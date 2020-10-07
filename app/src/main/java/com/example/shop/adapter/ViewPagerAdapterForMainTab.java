package com.example.shop.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shop.fragment.ALaCarte_Menu_Fragment;
import com.example.shop.fragment.For_One_Fragment;
import com.example.shop.fragment.For_Sharing_Fragment;
import com.example.shop.fragment.Hot_Deals_Fragment;

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
            case 0:
                return new For_One_Fragment(context);
            case 1:
                return new For_Sharing_Fragment(context);
            case 2:
                return new Hot_Deals_Fragment(context);
            case 3:
                return new ALaCarte_Menu_Fragment(context);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
