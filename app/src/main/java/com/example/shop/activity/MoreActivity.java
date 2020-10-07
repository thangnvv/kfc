package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.ultil.CustomDialogOperationPolicy;
import com.example.shop.ultil.CustomDialogPolicyAndRegulation;
import com.example.shop.ultil.CustomDialogTermsAndConditons;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MoreActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{

    BottomNavigationView mBtmNavigationView;
    TextView mTxtViewAppSetting, mTxtAboutKFC, mTxtViewContact, mTxtViewTermsAndConDitionsTitle, mTxtViewOperationPolicyTitle, mTxtViewPolicyAndRegulationTitle;
    ImageButton mImgButtonFacebook, mImgButtonYoutube, mImgButtonInstagram;
    ImageView mImgViewHotline;
    LinearLayout mLinearLayoutAboutKFC;
    boolean mAboutKFCIsClicked = false;
    private final int REQUEST_CODE_CALL_PHONE = 123;
    private boolean doubleBackToExitPressedOnce;
    private Handler mHandlerQuiteApp = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        initView();
        setOnClick();

    }

    private void setOnClick() {
        mBtmNavigationView.setSelectedItemId(R.id.more);
        mBtmNavigationView.setOnNavigationItemSelectedListener(this);
        mTxtViewAppSetting.setOnClickListener(this);
        mTxtAboutKFC.setOnClickListener(this);
        mTxtViewContact.setOnClickListener(this);
        mTxtViewTermsAndConDitionsTitle.setOnClickListener(this);
        mTxtViewOperationPolicyTitle.setOnClickListener(this);
        mTxtViewPolicyAndRegulationTitle.setOnClickListener(this);
        mImgButtonFacebook.setOnClickListener(this);
        mImgButtonYoutube.setOnClickListener(this);
        mImgButtonInstagram.setOnClickListener(this);
        mImgViewHotline.setOnClickListener(this);
    }

    private void initView() {
        mBtmNavigationView               = findViewById(R.id.bottomNavigationView);
        mTxtViewAppSetting               = findViewById(R.id.textViewAppSetting);
        mTxtAboutKFC                     = findViewById(R.id.textViewAboutKFC);
        mTxtViewContact                  = findViewById(R.id.textViewContact);
        mTxtViewTermsAndConDitionsTitle  = findViewById(R.id.textViewTermsAndConditionsTitle);
        mTxtViewOperationPolicyTitle     = findViewById(R.id.textViewOperationPolicyTitle);
        mTxtViewPolicyAndRegulationTitle = findViewById(R.id.textViewPolicyAndRegulationTitle);
        mLinearLayoutAboutKFC            = findViewById(R.id.linearAbouKFC);
        mImgButtonFacebook               = findViewById(R.id.imageButtonFacebook);
        mImgButtonYoutube                = findViewById(R.id.imageButtonYoutube);
        mImgButtonInstagram              = findViewById(R.id.imageButtonInstagram);
        mImgViewHotline                  = findViewById(R.id.imageViewHotline);
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

    @RequiresApi(api = Build.VERSION_CODES.M)
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
            case R.id.imageButtonFacebook:
                Intent intentFacebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/KFCVietnam/"));
                startActivity(intentFacebook);
                break;
            case R.id.imageButtonYoutube:
                Intent intentYoutube = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/KFCVietnam2011"));
                startActivity(intentYoutube);
                break;
            case R.id.imageButtonInstagram:
                Intent intentInstagram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/kfc_vietnam/"));
                startActivity(intentInstagram);
                break;
            case R.id.imageViewHotline:
                if(ContextCompat.checkSelfPermission(MoreActivity.this, Manifest.permission.CALL_PHONE)
                                                  == PackageManager.PERMISSION_GRANTED){
                Intent intentCallHotline = new Intent(Intent.ACTION_CALL);
                intentCallHotline.setData(Uri.parse("tel:19006886"));
                startActivity(intentCallHotline);
                }else{
                    Toast.makeText(this, "Vui lòng cho phép áp truy cập danh bạ để thực hiện cuộc gọi", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[] {Manifest.permission.CALL_PHONE} , REQUEST_CODE_CALL_PHONE );
                }
            break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE_CALL_PHONE && permissions.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Intent intentCallHotline = new Intent(Intent.ACTION_CALL);
            intentCallHotline.setData(Uri.parse("tel:19006886"));
            startActivity(intentCallHotline);
        }

    }

    //Click twice to quite the app
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