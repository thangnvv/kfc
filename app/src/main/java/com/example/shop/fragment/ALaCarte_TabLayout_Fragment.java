package com.example.shop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.shop.R;
import com.google.android.material.tabs.TabLayout;

public class ALaCarte_TabLayout_Fragment extends Fragment {

    TabLayout mTabLayoutMenuALaCarte;

    public ALaCarte_TabLayout_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a_la_carte_tab, container, false);

        mTabLayoutMenuALaCarte = view.findViewById(R.id.tabLayoutALaCarteLine);
        mTabLayoutMenuALaCarte.addTab(mTabLayoutMenuALaCarte.newTab().setText("Gà Rán - Quay"));
        mTabLayoutMenuALaCarte.addTab(mTabLayoutMenuALaCarte.newTab().setText("Cơm - Burger"));
        mTabLayoutMenuALaCarte.addTab(mTabLayoutMenuALaCarte.newTab().setText("Thức Ăn Nhẹ"));
        mTabLayoutMenuALaCarte.addTab(mTabLayoutMenuALaCarte.newTab().setText("Tráng Miệng - Thức Uống"));


        mTabLayoutMenuALaCarte.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ALaCarte_Menu_Fragment.mViewPagerMenuAlaCarte.setCurrentItem(tab.getPosition());
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
