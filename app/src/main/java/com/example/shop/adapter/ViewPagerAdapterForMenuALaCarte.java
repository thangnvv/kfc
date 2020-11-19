package com.example.shop.adapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shop.fragment.DessertsAndDrinksFragment;
import com.example.shop.fragment.FriedRoastedChickenFragment;
import com.example.shop.fragment.RiceBurgerFragment;
import com.example.shop.fragment.SnacksFragment;

public class ViewPagerAdapterForMenuALaCarte extends FragmentPagerAdapter{

    private FragmentManager fragmentManager;
    private int numOfTabs;
    private Context context;

    public ViewPagerAdapterForMenuALaCarte(FragmentManager fragmentManager, int numOfTabs, Context context){
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
                return new FriedRoastedChickenFragment(context);
            case 1:
                return new RiceBurgerFragment(context);
            case 2:
                return new SnacksFragment(context);
            case 3:
                return new DessertsAndDrinksFragment(context);
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
