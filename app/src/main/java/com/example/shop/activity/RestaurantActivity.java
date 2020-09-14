package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.adapter.RestaurantAdapter;
import com.example.shop.ultil.CustomDialogChooseLocation;
import com.example.shop.ultil.Restaurant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{

    BottomNavigationView mBtmNavigationView;
    TextView mTxtViewCity, mTxtViewDistrict;
    RecyclerView mRecyclerView;
    RestaurantAdapter mRestaurantAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Restaurant> mArrayListRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        createRestaurantList();
        initView();

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRestaurantAdapter);

        mBtmNavigationView.setSelectedItemId(R.id.restaurant);
        mBtmNavigationView.setOnNavigationItemSelectedListener(this);
        mTxtViewCity.setOnClickListener(this);


    }

    private void createRestaurantList() {
        mArrayListRestaurant = new ArrayList<>();

        mArrayListRestaurant.add(new Restaurant("KFC LOTTE GÒ VẤP", "Lô 1F17, tầng 1 , Lotte mart Gò Vấp, số 18, Phan Văn Trị, Phường 10, Gò Vấp, TP.HCM"));
        mArrayListRestaurant.add(new Restaurant("KFC EMART GÒ VẤP", "366 Phan Văn Trị,Phường 5,Quận Gò Vấp"));
        mArrayListRestaurant.add(new Restaurant("KFC LŨY BÁN BÍCH", "Số 01, Vườn Lài, Phường Phú Thọ Hòa, Quận Tân Phú, Tp.HCM"));
        mArrayListRestaurant.add(new Restaurant("KFC LÝ THƯỜNG KIỆT", "446 - 446A Lý Thường Kiệt, phường 7, quận Tân Bình, TPHCM"));
        mArrayListRestaurant.add(new Restaurant("KFC AEON MALL TÂN PHÚ", "Trung tâm mua sắm Aeon, số 30 đường Bờ Bao Tân Thắng, P.Sơn Kỳ, Q.Tân Phú, TP. HCM"));
        mArrayListRestaurant.add(new Restaurant("KFC HÓC MÔN", "Khu phố 5, Thị trấn Hóc Môn, huyện Hóc Môn, TP. HCM"));
        mArrayListRestaurant.add(new Restaurant("KFC PHÚ MỸ HƯNG - HCM", "số 11 (S41-1), Bùi Bằng Đoàn, Khu Phố Hưng Vượng 2 (R13), P.Tân Phong, Q.7, TPHCM"));
        mArrayListRestaurant.add(new Restaurant("KFC TRẦN NÃO", "72 Trần Não, P.Bình An, Quận 2, Tp. HCM"));

    }

    private void initView() {
        mTxtViewCity        = findViewById(R.id.textViewCity);
        mTxtViewDistrict    = findViewById(R.id.textViewDisTrict);
        mBtmNavigationView  = findViewById(R.id.bottomNavigationView);
        mRecyclerView       = findViewById(R.id.recyclerViewRestaurant);
        mLayoutManager      = new LinearLayoutManager(RestaurantActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRestaurantAdapter  = new RestaurantAdapter(mArrayListRestaurant, RestaurantActivity.this);
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
            case R.id.textViewCity:
                CustomDialogChooseLocation chooseCityDialog = new CustomDialogChooseLocation(RestaurantActivity.this);
                chooseCityDialog.show();
                break;
        }
    }
}