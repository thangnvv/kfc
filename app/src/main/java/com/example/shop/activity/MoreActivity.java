package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.ultil.CustomDialogOperationPolicy;
import com.example.shop.ultil.CustomDialogPolicyAndRegulation;
import com.example.shop.ultil.CustomDialogTermsAndConditons;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MoreActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{

    BottomNavigationView mBtmNavigationView;
    TextView mTxtViewAppSetting, mTxtAboutKFC, mTxtViewContact, mTxtViewTermsAndConDitionsTitle, mTxtViewOperationPolicyTitle, mTxtViewPolicyAndRegulationTitle;
    LinearLayout mLinearLayoutAboutKFC;
    boolean mAboutKFCIsClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        initView();

        mBtmNavigationView.setSelectedItemId(R.id.more);
        mBtmNavigationView.setOnNavigationItemSelectedListener(this);
        mTxtViewAppSetting.setOnClickListener(this);
        mTxtAboutKFC.setOnClickListener(this);
        mTxtViewContact.setOnClickListener(this);
        mTxtViewTermsAndConDitionsTitle.setOnClickListener(this);
        mTxtViewOperationPolicyTitle.setOnClickListener(this);
        mTxtViewPolicyAndRegulationTitle.setOnClickListener(this);
    }

    private void initView() {
        mBtmNavigationView          = findViewById(R.id.bottomNavigationView);
        mTxtViewAppSetting          = findViewById(R.id.textViewAppSetting);
        mTxtAboutKFC                = findViewById(R.id.textViewAboutKFC);
        mTxtViewContact             = findViewById(R.id.textViewContact);
        mTxtViewTermsAndConDitionsTitle  = findViewById(R.id.textViewTermsAndConditionsTitle);
        mTxtViewOperationPolicyTitle= findViewById(R.id.textViewOperationPolicyTitle);
        mTxtViewPolicyAndRegulationTitle = findViewById(R.id.textViewPolicyAndRegulationTitle);
        mLinearLayoutAboutKFC       = findViewById(R.id.linearAbouKFC);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textViewAppSetting:
                Intent intentSettingActitity = new Intent(MoreActivity.this, SettingActivity.class);
                startActivity(intentSettingActitity);
                break;
            case R.id.textViewAboutKFC:
                if(mAboutKFCIsClicked){
                    mLinearLayoutAboutKFC.setVisibility(View.GONE);
                    mAboutKFCIsClicked = false;
                }else{
                    mLinearLayoutAboutKFC.setVisibility(View.VISIBLE);
                    mAboutKFCIsClicked = true;
                }
                break;
            case R.id.textViewContact:
                Intent intentContact = new Intent(MoreActivity.this, ContactActivity.class);
                startActivity(intentContact);
                break;
            case R.id.textViewTermsAndConditionsTitle:
                CustomDialogTermsAndConditons customDialogTermsAndConditons = new CustomDialogTermsAndConditons(MoreActivity.this);
                customDialogTermsAndConditons.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                customDialogTermsAndConditons.show();
                break;
            case R.id.textViewOperationPolicyTitle:
                CustomDialogOperationPolicy customDialogOperationPolicy = new CustomDialogOperationPolicy(MoreActivity.this);
                customDialogOperationPolicy.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                customDialogOperationPolicy.show();
                break;
            case R.id.textViewPolicyAndRegulationTitle:
                CustomDialogPolicyAndRegulation customDialogPolicyAndRegulation = new CustomDialogPolicyAndRegulation(MoreActivity.this);
                customDialogPolicyAndRegulation.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                customDialogPolicyAndRegulation.show();
                break;
        }
    }
}