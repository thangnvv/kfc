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
import com.example.shop.utils.objects.Product;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.hawk.Hawk;

public class ALaCarteMenuFragment extends Fragment implements OnProductClickListener{

    public static ViewPager mViewPagerMenuAlaCarte;

    static ViewPagerAdapterForMenuALaCarte mViewPagerAdapterMenuAlaCarte;
    FragmentManager mFragmentManagerMenuAlaCarte;
    View view;
    OnProductClickListener onProductClickListener;
    Context context;

    public ALaCarteMenuFragment() {
        // Required empty public constructor
    }

    public ALaCarteMenuFragment(Context context) {
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
                TabLayout.Tab tab = ALaCarteTabLayoutFragment.mTabLayoutMenuALaCarte.getTabAt(position);
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
        if(fragment instanceof FriedRoastedChickenFragment){
            FriedRoastedChickenFragment fried_roastedChicken = (FriedRoastedChickenFragment) fragment;
            fried_roastedChicken.setProductClickListener(this);
            return fried_roastedChicken;
        }else if(fragment instanceof RiceBurgerFragment){
            RiceBurgerFragment rice_burger = (RiceBurgerFragment) fragment;
            rice_burger.setProductClickListener(this);
            return rice_burger;
        }else if(fragment instanceof SnacksFragment){
            SnacksFragment snacks_fragment = (SnacksFragment) fragment;
            snacks_fragment.setProductClickListener(this);
            return snacks_fragment;
        }else if(fragment instanceof DessertsAndDrinksFragment){
            DessertsAndDrinksFragment desserts_and_drinks_fragment = (DessertsAndDrinksFragment) fragment;
            desserts_and_drinks_fragment.setProductClickListener(this);
            return desserts_and_drinks_fragment;
        } else{
            Log.d("EEE", "Fragment is null");
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