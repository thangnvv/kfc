package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.adapter.RestaurantAdapter;
import com.example.shop.interfaces.OnSpinnerItemSelectedListener;
import com.example.shop.utils.CreateDistrictListHelper;
import com.example.shop.utils.CreateHtmlTextHelper;
import com.example.shop.utils.CreateRestaurantListHelper;
import com.example.shop.dialogs.CustomDialogChooseCity;
import com.example.shop.dialogs.CustomDialogChooseDistrict;
import com.example.shop.objects.Restaurant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{

    private BottomNavigationView mBtmNavigationView;
    private TextView mTxtViewCity, mTxtViewDistrict, mTxtViewFind;
    private RecyclerView mRecyclerView;
    private RestaurantAdapter mRestaurantAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Restaurant> mArrayListRestaurant, holdResData;

    private ConstraintLayout mLayoutCity, mLayoutDistrict;
    private boolean doubleBackToExitPressedOnce;
    private Handler mHandlerQuiteApp = new Handler();
    private String[] cityList;
    private int cityPosition = 0;
    private int districtPosition = 0;
    private Boolean citySelected = false;
    private Boolean districtSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        initView();

        mBtmNavigationView.setSelectedItemId(R.id.restaurant);
        mBtmNavigationView.setOnNavigationItemSelectedListener(this);
        mLayoutCity.setOnClickListener(this);
        mLayoutDistrict.setOnClickListener(this);
        mTxtViewFind.setOnClickListener(this);
        mLayoutDistrict.setClickable(false);

        mTxtViewDistrict.setText(CreateHtmlTextHelper.createTextRequired("Quận/Huyện"));
        mTxtViewCity.setText(CreateHtmlTextHelper.createTextRequired("Tỉnh/Thành Phố"));
        cityList = this.getResources().getStringArray(R.array.city);

    }

    private void initView() {
        mLayoutCity = findViewById(R.id.layoutCity);
        mLayoutDistrict = findViewById(R.id.layoutDistrict);
        mTxtViewCity        = findViewById(R.id.textViewCity);
        mTxtViewDistrict    = findViewById(R.id.textViewDisTrict);
        mTxtViewFind        = findViewById(R.id.textViewFind);
        mBtmNavigationView  = findViewById(R.id.bottomNavigationView);
        mRecyclerView       = findViewById(R.id.recyclerViewRestaurant);
        mLayoutManager      = new LinearLayoutManager(RestaurantActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mArrayListRestaurant = new ArrayList<>();
        holdResData         = new ArrayList<>();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.promotion:
                Intent intentPromotion = new Intent(RestaurantActivity.this, PromotionNewsActivity.class);
                startActivity(intentPromotion);
                finish();
                break;
            case R.id.more:
                Intent intentMore = new Intent(RestaurantActivity.this, MoreActivity.class);
                startActivity(intentMore);
                finish();
                break;
            case R.id.menu:
                Intent intentMenu = new Intent(RestaurantActivity.this, MainActivity.class);
                startActivity(intentMenu);
                finish();
        }


        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layoutCity:
                CustomDialogChooseCity chooseCityDialog = new CustomDialogChooseCity(RestaurantActivity.this);
                chooseCityDialog.setCancelable(false);
                chooseCityDialog.setOnItemSelectedListener(new OnSpinnerItemSelectedListener() {
                    @Override
                    public void onItemSelected(int position) {
                        if(position != 0){
                            mTxtViewCity.setText(CreateHtmlTextHelper.createTextRequiredForDialog("Tỉnh/Thành Phố" , cityList[position]));
                            mTxtViewCity.setMaxLines(Integer.MAX_VALUE);
                            citySelected = true;
                            cityPosition = position;
                            mLayoutDistrict.setClickable(citySelected);
                            if(districtSelected){
                                mTxtViewDistrict.setText(CreateHtmlTextHelper.createTextRequired("Quận/Huyện"));
                                districtSelected = false;
                                districtPosition = 0;
                            }
                            if(holdResData != null){
                                holdResData.clear();
                            }
                            holdResData.addAll(CreateRestaurantListHelper.createRestaurantListInCity(RestaurantActivity.this, position));
                        }else{
                            mTxtViewCity.setMaxLines(1);
                            mTxtViewCity.setText(CreateHtmlTextHelper.createTextRequired("Tỉnh/Thành Phố"));
                            citySelected = false;
                            cityPosition = 0;
                            mLayoutDistrict.setClickable(citySelected);
                            if(districtSelected == true){
                                mTxtViewDistrict.setText(CreateHtmlTextHelper.createTextRequired("Quận/Huyện"));
                                districtSelected = false;
                                districtPosition = 0;
                            }
                        }
                    }
                });
                chooseCityDialog.show();
                break;

            case R.id.layoutDistrict:
                CustomDialogChooseDistrict chooseDistrictDialog = new CustomDialogChooseDistrict(RestaurantActivity.this, cityPosition);
                chooseDistrictDialog.setCancelable(false);
                chooseDistrictDialog.setOnItemSelectedListener(new OnSpinnerItemSelectedListener() {
                    @Override
                    public void onItemSelected(int position) {
                        if(position != 0){
                            mTxtViewDistrict.setMaxLines(Integer.MAX_VALUE);
                            mTxtViewDistrict.setText(CreateHtmlTextHelper.createTextRequiredForDialog("Quận/Huyện", CreateDistrictListHelper.getDistrict(position)));
                            districtSelected = true;
                            districtPosition = position;
                        }else{
                            mTxtViewDistrict.setMaxLines(1);
                            mTxtViewDistrict.setText(CreateHtmlTextHelper.createTextRequired("Quận/Huyện"));
                            districtSelected = false;
                            districtPosition = 0;
                        }
                    }
                });
                chooseDistrictDialog.show();
                break;
            case R.id.textViewFind:
                if(citySelected == true &&  districtSelected == false ){
                    if(mArrayListRestaurant !=null){
                        mArrayListRestaurant.clear();
                        mArrayListRestaurant.addAll(CreateRestaurantListHelper.createRestaurantListInCity(RestaurantActivity.this, cityPosition));
                    }else{
                        mArrayListRestaurant = CreateRestaurantListHelper.createRestaurantListInCity(RestaurantActivity.this, cityPosition);
                    }
                }
                if(citySelected == true &&  districtSelected == true ){
                    if(mArrayListRestaurant !=null){
                        mArrayListRestaurant.clear();
                        mArrayListRestaurant.addAll(CreateRestaurantListHelper.createRestaurantListInDistrict(holdResData, CreateDistrictListHelper.getDistrict(districtPosition)));
                    }else{
                        mArrayListRestaurant.addAll(CreateRestaurantListHelper.createRestaurantListInDistrict(holdResData, CreateDistrictListHelper.getDistrict(districtPosition)));
                    }
                }
                if(mRestaurantAdapter == null){
                    mRestaurantAdapter = new RestaurantAdapter(mArrayListRestaurant, RestaurantActivity.this);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mRestaurantAdapter);
                }
                mRestaurantAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn thêm lần nữa để thoát App", Toast.LENGTH_SHORT).show();
        mHandlerQuiteApp.postDelayed(mRunnable, 2000);
    }

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandlerQuiteApp != null) { mHandlerQuiteApp.removeCallbacks(mRunnable); }
    }
}