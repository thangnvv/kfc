package com.example.shop.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shop.R;
import com.example.shop.adapter.ViewPagerAdapterForMenuALaCarte;
import com.example.shop.interfaces.OnListenChangeTab;

public class ALaCarte_Fragment extends Fragment implements OnListenChangeTab {

    ViewPager mViewPagerMenuAlaCarte;

    static ViewPagerAdapterForMenuALaCarte mViewPagerAdapterMenuAlaCarte;
    FragmentManager mFragmentManagerMenuAlaCarte;
    View view;

    public ALaCarte_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_a_la_carte_, container, false);

            mViewPagerMenuAlaCarte = view.findViewById(R.id.viewPagerMenuAlaCarte);
            mFragmentManagerMenuAlaCarte = getChildFragmentManager();
            mViewPagerAdapterMenuAlaCarte = new ViewPagerAdapterForMenuALaCarte(mFragmentManagerMenuAlaCarte, 4);
            mViewPagerMenuAlaCarte.setAdapter(mViewPagerAdapterMenuAlaCarte);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void setOnClickTab(int position) {

    }
}