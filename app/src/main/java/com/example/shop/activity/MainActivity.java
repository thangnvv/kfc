package com.example.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.example.shop.adapter.AdapterForSlider;
import com.example.shop.adapter.ViewPagerAdapterForTabLine;
import com.example.shop.adapter.ViewPagerAdapterForMainTab;
import com.example.shop.R;
import com.example.shop.ultil.BannerImage;
import com.example.shop.ultil.noneAllowSwipeViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.tbuonomo.morphbottomnavigation.MorphBottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ImageView mImgViewMore;
    Toolbar mToolbarMain;
    TabLayout mTabLayoutMain;
    noneAllowSwipeViewPager mViewPagerLine, mViewPagerMain;
    ViewPagerAdapterForTabLine viewPagerAdapterForTabLine;
    ViewPagerAdapterForMainTab viewPagerAdapterForMainTab;
    MorphBottomNavigationView mBtmNavigationView;

    //For slider banner in main
    private List<BannerImage> bannerMain;
    private SliderView sliderView;
    private AdapterForSlider adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        setSupportActionBar(mToolbarMain);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mBtmNavigationView.setOnNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentManager fragmentManagerMain = getSupportFragmentManager();

        viewPagerAdapterForTabLine = new ViewPagerAdapterForTabLine(fragmentManager, mTabLayoutMain.getTabCount());
        viewPagerAdapterForMainTab = new ViewPagerAdapterForMainTab(fragmentManagerMain, mTabLayoutMain.getTabCount());
        Log.d("DDD", mTabLayoutMain.getTabCount() + " Số tab");
        mViewPagerLine.setAdapter(viewPagerAdapterForTabLine);
        mViewPagerMain.setAdapter(viewPagerAdapterForMainTab);

        mViewPagerLine.setOffscreenPageLimit(2);
        mViewPagerMain.setOffscreenPageLimit(2);

        mViewPagerLine.setPagingEnabled(false);
        mViewPagerMain.setPagingEnabled(false);

        mViewPagerLine.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayoutMain));

        mTabLayoutMain.setTabTextColors(ContextCompat.getColorStateList(this, R.color.black));
        mImgViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMore = new Intent(MainActivity.this , MoreActivity.class);
                startActivity(intentMore);
            }
        });

        mTabLayoutMain.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPagerLine.setCurrentItem(tab.getPosition());
                mViewPagerMain.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        adapter = new AdapterForSlider(this, bannerMain);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

    }

    private void initView() {
        sliderView             = findViewById(R.id.imageSlider);
        mToolbarMain           = findViewById(R.id.toolBarMain);
        mImgViewMore           = findViewById(R.id.imageViewMore);
        mViewPagerMain         = findViewById(R.id.viewPagerMain);
        mTabLayoutMain         = findViewById(R.id.tabLayoutMain);
        mViewPagerLine         = findViewById(R.id.viewPagerLine);
        mBtmNavigationView     = findViewById(R.id.bottomNavigationView);

        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("Combo 1 người"));
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("Combo nhóm"));
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("Menu ưu đãi"));
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("Món lẻ"));



        bannerMain = new ArrayList<>();
        bannerMain.add(new BannerImage("https://kfcvietnam.com.vn/uploads/banner/900f8a45e0d5c36e45ad0dcffd9e054f.png"));
        bannerMain.add(new BannerImage("https://kfcvietnam.com.vn/uploads/banner/bf8393c04ca093bf595e0af0295217b0.png"));
        bannerMain.add(new BannerImage("https://kfcvietnam.com.vn/uploads/banner/182b708be1229785fb606ac48660b852.png"));
        bannerMain.add(new BannerImage("https://kfcvietnam.com.vn/uploads/banner/7185c7da5835e592c4b86cdd4725c171.png"));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.promotion:
                Intent intentPromotion = new Intent(MainActivity.this, PromotionNewsActivity.class);
                startActivity(intentPromotion);
                break;
            case R.id.more:
                Intent intentMore = new Intent(MainActivity.this, MoreActivity.class);
                startActivity(intentMore);
                break;
        }
        return true;
    }
}
