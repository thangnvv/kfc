package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.adapter.RestaurantAdapter;
import com.example.shop.interfaces.OnSpinnerItemSelectedListener;
import com.example.shop.ultil.CreateDistrictList;
import com.example.shop.ultil.CreateHtmlText;
import com.example.shop.ultil.CreateRestaurantListWithCity;
import com.example.shop.ultil.CreateRestaurantListWithDistrict;
import com.example.shop.ultil.CustomDialogChooseCity;
import com.example.shop.ultil.CustomDialogChooseDistrict;
import com.example.shop.ultil.Restaurant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{

    BottomNavigationView mBtmNavigationView;
    TextView mTxtViewCity, mTxtViewDistrict, mTxtViewFind;
    RecyclerView mRecyclerView;
    RestaurantAdapter mRestaurantAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Restaurant> mArrayListRestaurant, holdResData;

    LinearLayout mLinearLayoutCity, mLinearLayoutDistrict;
    private boolean doubleBackToExitPressedOnce;
    private Handler mHandlerQuiteApp = new Handler();
    private String[] cityList;
    private String[] districtList;
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
        mLinearLayoutCity.setOnClickListener(this);
        mLinearLayoutDistrict.setOnClickListener(this);
        mTxtViewFind.setOnClickListener(this);
        mLinearLayoutDistrict.setClickable(false);

        mTxtViewDistrict.setText(CreateHtmlText.createTextRequired("Quận/Huyện"));
        cityList = this.getResources().getStringArray(R.array.city);

    }

    private void initView() {
        mLinearLayoutCity   = findViewById(R.id.linearLayoutCity);
        mLinearLayoutDistrict = findViewById(R.id.linearLayoutDistrict);
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
            case R.id.linearLayoutCity:
                CustomDialogChooseCity chooseCityDialog = new CustomDialogChooseCity(RestaurantActivity.this);
                chooseCityDialog.setCancelable(false);
                chooseCityDialog.setOnItemSelectedListener(new OnSpinnerItemSelectedListener() {
                    @Override
                    public void onItemSelectedListener(int position) {
                        if(position != 0){
                            mTxtViewCity.setText("Tỉnh/Thành Phố" + "\n" + cityList[position]);
                            citySelected = true;
                            cityPosition = position;
                            mLinearLayoutDistrict.setClickable(citySelected);
                            if(districtSelected){
                                mTxtViewDistrict.setText(CreateHtmlText.createTextRequired("Quận/Huyện"));
                                districtSelected = false;
                                districtPosition = 0;
                            }
                            if(holdResData != null){
                                holdResData.clear();
                            }
                            holdResData.addAll(CreateRestaurantListWithCity.createRestaurantList(RestaurantActivity.this, position));
                        }else{
                            mTxtViewCity.setText("Tỉnh/Thành Phố");
                            citySelected = false;
                            cityPosition = 0;
                            mLinearLayoutDistrict.setClickable(citySelected);
                            if(districtSelected == true){
                                mTxtViewDistrict.setText(CreateHtmlText.createTextRequired("Quận/Huyện"));
                                districtSelected = false;
                                districtPosition = 0;
                            }
                        }
                    }
                });
                chooseCityDialog.show();
                break;

            case R.id.linearLayoutDistrict:
                CustomDialogChooseDistrict chooseDistrictDialog = new CustomDialogChooseDistrict(RestaurantActivity.this, cityPosition);
                chooseDistrictDialog.setCancelable(false);
                chooseDistrictDialog.setOnItemSelectedListener(new OnSpinnerItemSelectedListener() {
                    @Override
                    public void onItemSelectedListener(int position) {
                        if(position != 0){
                            mTxtViewDistrict.setText(Html.fromHtml("<font color='#707070'>Quận/Huyện </font><font color='#e4002b'>*</font>") + "\n" + CreateDistrictList.getDistrict(position));
                            districtSelected = true;
                            districtPosition = position;
                        }else{
                            mTxtViewDistrict.setText(CreateHtmlText.createTextRequired("Quận/Huyện"));
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
                        mArrayListRestaurant.addAll(CreateRestaurantListWithCity.createRestaurantList(RestaurantActivity.this, cityPosition));
                    }else{
                        mArrayListRestaurant = CreateRestaurantListWithCity.createRestaurantList(RestaurantActivity.this, cityPosition);
                    }
                }
                if(citySelected == true &&  districtSelected == true ){
                    if(mArrayListRestaurant !=null){
                        mArrayListRestaurant.clear();
                        mArrayListRestaurant.addAll(CreateRestaurantListWithDistrict.createRestaurantListWithDistrict(holdResData,CreateDistrictList.getDistrict(districtPosition)));
                    }else{
                        mArrayListRestaurant.addAll(CreateRestaurantListWithDistrict.createRestaurantListWithDistrict(holdResData,CreateDistrictList.getDistrict(districtPosition)));
                    }
                }
                if(mRestaurantAdapter == null){
                    mRestaurantAdapter  = new RestaurantAdapter(mArrayListRestaurant, RestaurantActivity.this);
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