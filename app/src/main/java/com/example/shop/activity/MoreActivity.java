package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.ultil.CustomDialogOperationPolicy;
import com.example.shop.ultil.CustomDialogPolicyAndRegulation;
import com.example.shop.ultil.CustomDialogTermsAndConditons;
import com.example.shop.ultil.Customer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MoreActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {

    // Showing layout base on Sign In status
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDataRef = FirebaseDatabase.getInstance().getReference();
    LinearLayout mLlSignedUp, mLlSignInAndSignUp;
    TextView mTxtViewUserName, mTxtViewSignOut;
    Button mBtnSignIn, mBtnSignUp;

    BottomNavigationView mBtmNavigationView;
    TextView mTxtViewAppSetting, mTxtAboutKFC, mTxtViewContact, mTxtViewTermsAndConditionsTitle, mTxtViewOperationPolicyTitle, mTxtViewPolicyAndRegulationTitle;
    TextView mTxtViewMyAccount, mTxtViewOrderHistory, mTxtViewShippingAddress;
    LinearLayout mLlAccountInformation;
    TextView mTxtViewReceiptNotification, mTxtViewChangePassword, mTxtViewChangeAccountInformation;
    ImageButton mImgButtonFacebook, mImgButtonYoutube, mImgButtonInstagram;
    ImageView mImgViewHotline;
    LinearLayout mLlAboutKFC;
    boolean mAboutKFCIsClicked = false, mAccountInfoIsClick = false;
    private final int REQUEST_CODE_CALL_PHONE = 111;
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
        mTxtAboutKFC.setOnClickListener(this);
        mTxtViewTermsAndConditionsTitle.setOnClickListener(this);
        mTxtViewOperationPolicyTitle.setOnClickListener(this);
        mTxtViewPolicyAndRegulationTitle.setOnClickListener(this);
        mTxtViewContact.setOnClickListener(this);

        mBtmNavigationView.setSelectedItemId(R.id.more);
        mBtmNavigationView.setOnNavigationItemSelectedListener(this);

        mTxtViewAppSetting.setOnClickListener(this);

        mImgButtonFacebook.setOnClickListener(this);
        mImgButtonYoutube.setOnClickListener(this);
        mImgButtonInstagram.setOnClickListener(this);
        mImgViewHotline.setOnClickListener(this);

        mBtnSignUp.setOnClickListener(this);
        mBtnSignIn.setOnClickListener(this);
        mTxtViewSignOut.setOnClickListener(this);

        mTxtViewChangePassword.setOnClickListener(this);
        mTxtViewReceiptNotification.setOnClickListener(this);
        mTxtViewChangeAccountInformation.setOnClickListener(this);
    }

    private void initView() {
        mBtmNavigationView = findViewById(R.id.bottomNavigationView);
        mTxtViewAppSetting = findViewById(R.id.textViewAppSetting);

        mLlAboutKFC = findViewById(R.id.linearAbouKFC);
        mTxtAboutKFC = findViewById(R.id.textViewAboutKFC);
        mTxtViewContact = findViewById(R.id.textViewContact);
        mTxtViewPolicyAndRegulationTitle = findViewById(R.id.textViewPolicyAndRegulationTitle);
        mTxtViewTermsAndConditionsTitle = findViewById(R.id.textViewTermsAndConditionsTitle);
        mTxtViewOperationPolicyTitle = findViewById(R.id.textViewOperationPolicyTitle);

        mLlAccountInformation = findViewById(R.id.linearLayoutAccountInformation);
        mTxtViewChangeAccountInformation = findViewById(R.id.textViewChangeAccountInformation);
        mTxtViewReceiptNotification = findViewById(R.id.textViewReceiptNotification);
        mTxtViewChangePassword = findViewById(R.id.textViewChangePassword);

        mImgButtonFacebook = findViewById(R.id.imageButtonFacebook);
        mImgButtonYoutube = findViewById(R.id.imageButtonYoutube);
        mImgButtonInstagram = findViewById(R.id.imageButtonInstagram);
        mImgViewHotline = findViewById(R.id.imageViewHotline);

        // Include Sign In Sign Up
        mLlSignedUp = findViewById(R.id.linearLayoutSignedUp);
        mLlSignInAndSignUp = findViewById(R.id.linearLayoutSignInAndSignUp);
        mBtnSignUp = findViewById(R.id.buttonSignUp);
        mBtnSignIn = findViewById(R.id.buttonSignIn);
        mTxtViewUserName = findViewById(R.id.textViewUserName);
        mTxtViewSignOut = findViewById(R.id.textViewSignOut);
        mTxtViewShippingAddress = findViewById(R.id.textViewShippingAddress);
        mTxtViewOrderHistory = findViewById(R.id.textViewOrderHistory);
        mTxtViewMyAccount = findViewById(R.id.textViewMyAccount);
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
        switch (v.getId()) {
            case R.id.textViewAppSetting:
                Intent intentSettingActitity = new Intent(MoreActivity.this, SettingActivity.class);
                startActivity(intentSettingActitity);
                break;
            case R.id.textViewAboutKFC:
                if (mAboutKFCIsClicked) {
                    mLlAboutKFC.setVisibility(View.GONE);
                    mAboutKFCIsClicked = false;
                } else {
                    mLlAboutKFC.setVisibility(View.VISIBLE);
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
                if (ContextCompat.checkSelfPermission(MoreActivity.this, Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    Intent intentCallHotline = new Intent(Intent.ACTION_CALL);
                    intentCallHotline.setData(Uri.parse("tel:19006886"));
                    startActivity(intentCallHotline);
                } else {
                    Toast.makeText(this, "Vui lòng cho phép áp truy cập danh bạ để thực hiện cuộc gọi", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_CALL_PHONE);
                }
                break;
            case R.id.buttonSignIn:
                Intent intentSignIn = new Intent(MoreActivity.this, SignInActivity.class);
                startActivity(intentSignIn);
                break;
            case R.id.buttonSignUp:
                Intent intentSignUp = new Intent(MoreActivity.this, AccountRegisterActivity.class);
                startActivity(intentSignUp);
                break;
            case R.id.textViewSignOut:
                FirebaseAuth.getInstance().signOut();
                mLlSignInAndSignUp.setVisibility(View.VISIBLE);
                mLlSignedUp.setVisibility(View.GONE);
                mTxtViewSignOut.setVisibility(View.GONE);
                mTxtViewMyAccount.setTextColor(AppCompatResources.getColorStateList(MoreActivity.this, R.color.darkGrey));
                mTxtViewMyAccount.setClickable(false);
                mTxtViewOrderHistory.setTextColor(AppCompatResources.getColorStateList(MoreActivity.this, R.color.darkGrey));
                mTxtViewOrderHistory.setClickable(false);
                mTxtViewShippingAddress.setTextColor(AppCompatResources.getColorStateList(MoreActivity.this, R.color.darkGrey));
                mTxtViewShippingAddress.setClickable(false);
                if(mAccountInfoIsClick){
                    mLlAccountInformation.setVisibility(View.GONE);
                    mAccountInfoIsClick = false;
                }

                break;
            case R.id.textViewChangePassword:
                startActivity(new Intent(MoreActivity.this, ChangePasswordActivity.class));
                break;
            case R.id.textViewChangeAccountInformation:
            case R.id.textViewReceiptNotification:
                Toast.makeText(MoreActivity.this, "Chức năng đang được cập nhật. Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_CALL_PHONE && permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intentCallHotline = new Intent(Intent.ACTION_CALL);
            intentCallHotline.setData(Uri.parse("tel:19006886"));
            startActivity(intentCallHotline);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            mTxtViewMyAccount.setTextColor(getResources().getColor(R.color.black));
            mTxtViewMyAccount.setClickable(true);
            mTxtViewOrderHistory.setTextColor(getResources().getColor(R.color.black));
            mTxtViewOrderHistory.setClickable(true);
            mTxtViewShippingAddress.setTextColor(getResources().getColor(R.color.black));
            mTxtViewShippingAddress.setClickable(true);

            mTxtViewOrderHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MoreActivity.this, OrderHistoryActivity.class));
                }
            });

            mTxtViewShippingAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MoreActivity.this, "Chức năng đang được nâng cấp, vui lòng thử lại sau." , Toast.LENGTH_SHORT).show();
                }
            });

            mTxtViewMyAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mAccountInfoIsClick){
                        mLlAccountInformation.setVisibility(View.GONE);
                        mAccountInfoIsClick = false;
                    }else{
                        mLlAccountInformation.setVisibility(View.VISIBLE);
                        mAccountInfoIsClick = true;
                    }
                }
            });

            mLlSignInAndSignUp.setVisibility(View.GONE);
            mLlSignedUp.setVisibility(View.VISIBLE);
            mTxtViewSignOut.setVisibility(View.VISIBLE);
            String userUrl = "user/" + currentUser.getUid();
            mDataRef.child(userUrl).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Customer customer = snapshot.getValue(Customer.class);
                    mTxtViewUserName.setText(customer.getFull_name());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else {
            mLlSignedUp.setVisibility(View.GONE);
            mLlSignInAndSignUp.setVisibility(View.VISIBLE);
            mTxtViewMyAccount.setTextColor(AppCompatResources.getColorStateList(MoreActivity.this, R.color.darkGrey));
            mTxtViewMyAccount.setClickable(false);
            mTxtViewOrderHistory.setTextColor(AppCompatResources.getColorStateList(MoreActivity.this, R.color.darkGrey));
            mTxtViewOrderHistory.setClickable(false);
            mTxtViewShippingAddress.setTextColor(AppCompatResources.getColorStateList(MoreActivity.this, R.color.darkGrey));
            mTxtViewShippingAddress.setClickable(false);
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
        if (mHandlerQuiteApp != null) {
            mHandlerQuiteApp.removeCallbacks(mRunnable);
        }
    }
}