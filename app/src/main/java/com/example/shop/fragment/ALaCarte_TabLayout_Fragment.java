package com.example.shop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.shop.R;
import com.example.shop.adapter.ViewPagerAdapterForMenuALaCarte;
import com.example.shop.interfaces.OnListenChangeTab;
import com.google.android.material.tabs.TabLayout;

public class ALaCarte_TabLayout_Fragment extends Fragment {

    TabLayout mTabLayoutMenuALaCarte;
    ViewPager mViewPagertest;
    ViewPagerAdapterForMenuALaCarte mViewPagerAdapterTest;
    FragmentManager fragmentManager;

    public ALaCarte_TabLayout_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a_la_carte_line, container, false);

        mTabLayoutMenuALaCarte = view.findViewById(R.id.tabLayoutALaCarteLine);
        mViewPagertest         = view.findViewById(R.id.viewPagerTest);
        fragmentManager = getChildFragmentManager();

        mTabLayoutMenuALaCarte.addTab(mTabLayoutMenuALaCarte.newTab().setText("Gà Rán - Quay"));
        mTabLayoutMenuALaCarte.addTab(mTabLayoutMenuALaCarte.newTab().setText("Cơm - Burger"));
        mTabLayoutMenuALaCarte.addTab(mTabLayoutMenuALaCarte.newTab().setText("Thức Ăn Nhẹ"));
        mTabLayoutMenuALaCarte.addTab(mTabLayoutMenuALaCarte.newTab().setText("Tráng Miệng - Thức Uống"));
        mViewPagerAdapterTest  = new ViewPagerAdapterForMenuALaCarte(fragmentManager , mTabLayoutMenuALaCarte.getTabCount());
        mViewPagertest.setAdapter(mViewPagerAdapterTest);

        mTabLayoutMenuALaCarte.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("DDD" , "At Test");
                mViewPagertest.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}
