package com.example.shop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shop.R;
import com.example.shop.adapter.ViewPagerAdapterForMenuALaCarte;
import com.google.android.material.tabs.TabLayout;

public class ALaCarte_Menu_Fragment extends Fragment {

    public static ViewPager mViewPagerMenuAlaCarte;

    static ViewPagerAdapterForMenuALaCarte mViewPagerAdapterMenuAlaCarte;
    FragmentManager mFragmentManagerMenuAlaCarte;
    View view;

    public ALaCarte_Menu_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_a_la_carte_menu, container, false);

        mViewPagerMenuAlaCarte = view.findViewById(R.id.viewPagerMenuAlaCarte);
        mFragmentManagerMenuAlaCarte = getChildFragmentManager();
        mViewPagerAdapterMenuAlaCarte = new ViewPagerAdapterForMenuALaCarte(mFragmentManagerMenuAlaCarte, 4);
        mViewPagerMenuAlaCarte.setAdapter(mViewPagerAdapterMenuAlaCarte);
        mViewPagerMenuAlaCarte.setOffscreenPageLimit(1);
        mViewPagerMenuAlaCarte.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    TabLayout.Tab tab = ALaCarte_TabLayout_Fragment.mTabLayoutMenuALaCarte.getTabAt(position);
                    tab.select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }


}