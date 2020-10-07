package com.example.shop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shop.R;
import com.example.shop.ultil.Product;
import com.orhanobut.hawk.Hawk;

public class SettingProductActivity extends AppCompatActivity {
    RecyclerView mRclViewProductInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_product);
        initView();
        Product productSetting = Hawk.get("productSetting");
    }

    private void initView() {
        mRclViewProductInfo = findViewById(R.id.recyclerViewProductInfo);
    }
}