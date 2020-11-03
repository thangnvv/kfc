package com.example.shop.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shop.R;
import com.example.shop.adapter.ViewPagerAdapterForMenuALaCarte;
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.ultil.Product;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.hawk.Hawk;

public class ALaCarte_Menu_Fragment extends Fragment implements OnProductClickListener{

    public static ViewPager mViewPagerMenuAlaCarte;

    static ViewPagerAdapterForMenuALaCarte mViewPagerAdapterMenuAlaCarte;
    FragmentManager mFragmentManagerMenuAlaCarte;
    View view;
    OnProductClickListener onProductClickListener;
    Context context;

    public ALaCarte_Menu_Fragment() {
        // Required empty public constructor
    }

    public ALaCarte_Menu_Fragment(Context context) {
        this.context = context;
    }

    public void setOnProductClickListener(OnProductClickListener onProductClickListener){
        this.onProductClickListener = onProductClickListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_a_la_carte_menu, container, false);

        mViewPagerMenuAlaCarte = view.findViewById(R.id.viewPagerMenuAlaCarte);
        mFragmentManagerMenuAlaCarte = getChildFragmentManager();
        mViewPagerAdapterMenuAlaCarte = new ViewPagerAdapterForMenuALaCarte(mFragmentManagerMenuAlaCarte, 4, context);
        mViewPagerMenuAlaCarte.setAdapter(mViewPagerAdapterMenuAlaCarte);
        mViewPagerMenuAlaCarte.setOffscreenPageLimit(1);
        mViewPagerMenuAlaCarte.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
        setChildFragment(childFragment);
    }

    public Fragment setChildFragment(Fragment fragment){
        if(fragment instanceof Fried_RoastedChicken_Fragment){
            Fried_RoastedChicken_Fragment fried_roastedChicken = (Fried_RoastedChicken_Fragment) fragment;
            fried_roastedChicken.setProductClickListener(this);
            return fried_roastedChicken;
        }else if(fragment instanceof Rice_Burger_Fragment){
            Rice_Burger_Fragment rice_burger = (Rice_Burger_Fragment) fragment;
            rice_burger.setProductClickListener(this);
            return rice_burger;
        }else if(fragment instanceof Snacks_Fragment){
            Snacks_Fragment snacks_fragment = (Snacks_Fragment) fragment;
            snacks_fragment.setProductClickListener(this);
            return snacks_fragment;
        }else if(fragment instanceof Desserts_And_Drinks_Fragment){
            Desserts_And_Drinks_Fragment desserts_and_drinks_fragment = (Desserts_And_Drinks_Fragment) fragment;
            desserts_and_drinks_fragment.setProductClickListener(this);
            return desserts_and_drinks_fragment;
        } else{
            Log.d("DDD", "Fragment is null");
            return null;
        }
    }

    @Override
    public void onSettingProduct(Product product) {
        Hawk.put("productSetting", product);
        onProductClickListener.onSettingProduct(product);
    }

    @Override
    public void onOrderProduct(Product product) {
        onProductClickListener.onOrderProduct(product);
    }

}