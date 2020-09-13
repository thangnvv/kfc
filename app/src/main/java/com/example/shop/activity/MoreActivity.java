package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.shop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class MoreActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView mBtmNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        initView();

        mBtmNavigationView.setSelectedItemId(R.id.more);
        mBtmNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void initView() {
        mBtmNavigationView = findViewById(R.id.bottomNavigationView);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.promotion:
                Intent intentPromotion = new Intent(MoreActivity.this, PromotionNewsActivity.class);
                startActivity(intentPromotion);
                finish();
                break;
            case R.id.menu:
                Intent intentMenu = new Intent(MoreActivity.this, MainActivity.class);
                startActivity(intentMenu);
                finish();
                break;
            case R.id.restaurant:
                Intent intentRestaurant = new Intent(MoreActivity.this, RestaurantActivity.class);
                startActivity(intentRestaurant);
                finish();
        }
        return true;
    }
}