package com.example.shop.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shop.R;
import com.example.shop.adapter.ViewPagerAdapterForMenuALaCarte;
import com.example.shop.interfaces.OnListenChangeTab;

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

        return view;
    }


}