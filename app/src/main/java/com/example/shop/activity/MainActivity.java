package com.example.shop.activity;

import com.example.shop.adapter.OrderLineAdapter;
import com.example.shop.fragment.ALaCarte_Menu_Fragment;
import com.example.shop.fragment.For_One_Fragment;
import com.example.shop.fragment.For_Sharing_Fragment;
import com.example.shop.fragment.Hot_Deals_Fragment;
import com.example.shop.interfaces.OnALaCarteProductClickListener;
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.interfaces.OnSpinnerItemSelectedListener;
import com.example.shop.ultil.CustomDialogStartOrdering;
import com.example.shop.ultil.Product;
import com.example.shop.ultil.Util;
import com.facebook.FacebookSdk;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.adapter.AdapterForSlider;
import com.example.shop.adapter.ViewPagerAdapterForTabLine;
import com.example.shop.adapter.ViewPagerAdapterForMainTab;
import com.example.shop.R;
import com.example.shop.ultil.BannerImage;
import com.example.shop.ultil.noneAllowSwipeViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.hawk.Hawk;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        TabLayout.OnTabSelectedListener, View.OnClickListener, OnProductClickListener, OnALaCarteProductClickListener{

    TextView mTxtViewChangeCity, mTxtViewCity, mTxtViewCartTotal, mTxtViewCartCount;
    ImageView mImgViewMore, mImgViewCart;
    Toolbar mToolbarMain;
    TabLayout mTabLayoutMain;
    noneAllowSwipeViewPager mViewPagerLine, mViewPagerMain;
    ViewPagerAdapterForTabLine viewPagerAdapterForTabLine;
    ViewPagerAdapterForMainTab viewPagerAdapterForMainTab;
    BottomNavigationView mBtmNavigationView;
    Button mBtnSignIn, mBtnSignUp;
    LinearLayout mainActivity, linearLayoutCart;

    // Include layout of Product Added To Cart
    ImageButton mImgButtonCancel;
    RecyclerView recyclerViewOrderInfo;
    Button mBtnCheckOut;
    RelativeLayout relativeLayoutProductAddedToCart;
    ArrayList<Product> mProductArrList, mProductAddedToCartArrList;
    OrderLineAdapter mOrderLineAdapter;
    boolean isShowing = false;

    private int cartCount = 0;
    private int cartTotal = 0;
    private boolean cartEmpty = true;

    //For slider banner in main
    private List<BannerImage> bannerMain;
    private SliderView sliderView;
    private AdapterForSlider adapter;

    private boolean doubleBackToExitPressedOnce;
    private Handler mHandlerQuiteApp = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        setUpActionBar();
        setUpNavigationView();
        setUpSliderBanner();
        setUpTabLayoutMain();
        setUpViewPager();
        setViewOnClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageViewMore:
                Intent intentMore = new Intent(MainActivity.this , MoreActivity.class);
                startActivity(intentMore);
                break;
            case R.id.buttonSignUp:
                Intent intentSignUp = new Intent (MainActivity.this, AccountRegisterActivity.class);
                startActivity(intentSignUp);
                break;
            case R.id.textViewChangeCity :
                final String[] cityList = getResources().getStringArray(R.array.city);
                final CustomDialogStartOrdering customDialogStartOrdering = new CustomDialogStartOrdering(MainActivity.this);
                customDialogStartOrdering.setCancelable(false);
                customDialogStartOrdering.setOnItemSelectedListener(new OnSpinnerItemSelectedListener() {
                    @Override
                    public void onItemSelectedListener(int position) {
                        mTxtViewCity.setText(cityList[position]);
                    }
                });
                customDialogStartOrdering.show();
                break;
            case R.id.imageButtonCancel:
                relativeLayoutProductAddedToCart.setVisibility(View.GONE);
                isShowing = false;
                break;
            case R.id.linearLayoutCart:
                Hawk.put("productAddedToCart" , mProductAddedToCartArrList);
                Hawk.put("productCount", cartCount);
                Hawk.put("cartTotal", mTxtViewCartTotal.getText());
                Intent intentCart = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intentCart);
                break;
        }
    }

    @Override
    public void onProductDetailClickListener(Product product) {

    }

    @Override
    public void onProductOrderClickListener(Product product) {
        addToCard(product.getFoodPrice());
        showProductAddedToCart(product);
    }

    private void showProductAddedToCart(Product product) {
        relativeLayoutProductAddedToCart.setVisibility(View.VISIBLE);
        relativeLayoutProductAddedToCart.bringToFront();
        relativeLayoutProductAddedToCart.setElevation(getResources().getDimension(R.dimen._4sdp));
        if(mProductArrList != null){
            mProductArrList.clear();
        }
        mProductArrList.add(product);
        mOrderLineAdapter.notifyDataSetChanged();
        mProductAddedToCartArrList.add(product);
        isShowing = true;
    }

    @Override
    public void onALaCarteOrderClickListener(String foodPrice) {
        addToCard(foodPrice);
    }

    private void addToCard(String foodPrice){
        if(cartEmpty){
            cartCount = 1;
            mTxtViewCartTotal.setText(foodPrice);
            mTxtViewCartCount.setText(cartCount + "");
            mTxtViewCartCount.setTextColor(getResources().getColor(R.color.white));
            mImgViewCart.setImageDrawable(getResources().getDrawable(R.drawable.cart_icon_active));
            cartTotal = Util.convertStringToInt(foodPrice);
            cartEmpty = false;
        }else{
            cartTotal = cartTotal + Util.convertStringToInt(foodPrice);
            cartCount++;
            mTxtViewCartCount.setText(cartCount + "");
            mTxtViewCartTotal.setText(Util.convertIntToString(cartTotal));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("DDD", "On Main Restart");
        int cart = Hawk.get("productCount");
        if(cartCount != cart) {
            cartCount = Hawk.get("productCount");
            String cartTotalText = Hawk.get("cartTotal");
            mProductArrList.clear();
            mProductAddedToCartArrList.clear();
            ArrayList<Product> arr = Hawk.get("productAddedToCart");
            mProductArrList.addAll(arr);
            mProductAddedToCartArrList.addAll(arr);
            mTxtViewCartCount.setText(cartCount + "");
            mTxtViewCartTotal.setText(cartTotalText);
            cartTotal = Util.convertStringToInt(cartTotalText);
        }
        if(cart == 0){
            mTxtViewCartCount.setTextColor(getResources().getColor(R.color.black));
            mImgViewCart.setImageDrawable(getResources().getDrawable(R.drawable.cart_icon));
            mTxtViewCartTotal.setText("Giỏ hàng");
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        getShowingFragment(fragment);
    }

    private void getShowingFragment(Fragment fragment) {
        if(fragment instanceof For_One_Fragment){
            For_One_Fragment for_one = (For_One_Fragment) fragment;
            for_one.setProductClickListener(this);
        }else if(fragment instanceof For_Sharing_Fragment){
            For_Sharing_Fragment for_sharing = (For_Sharing_Fragment) fragment;
            for_sharing.setProductClickListener(this);
        }else if(fragment instanceof Hot_Deals_Fragment){
            Hot_Deals_Fragment hot_deals = (Hot_Deals_Fragment) fragment;
            hot_deals.setProductClickListener(this);
        }else if(fragment instanceof ALaCarte_Menu_Fragment){
            ALaCarte_Menu_Fragment aLaCarte_menu = (ALaCarte_Menu_Fragment) fragment;
            aLaCarte_menu.setOnALaCarteProductClickListener(this);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.promotion:
                Intent intentPromotion = new Intent(MainActivity.this, PromotionNewsActivity.class);
                startActivity(intentPromotion);
                finish();
                break;
            case R.id.more:
                Intent intentMore = new Intent(MainActivity.this, MoreActivity.class);
                startActivity(intentMore);
                finish();
                break;
            case R.id.restaurant:
                Intent intentRestaurant = new Intent(MainActivity.this, RestaurantActivity.class);
                startActivity(intentRestaurant);
                finish();
        }
        return true;
    }

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

    private void setViewOnClick() {
        mImgViewMore.setOnClickListener(this);
        mBtnSignUp.setOnClickListener(this);
        mTxtViewChangeCity.setOnClickListener(this);
        mTabLayoutMain.addOnTabSelectedListener(this);
        mImgButtonCancel.setOnClickListener(this);
        linearLayoutCart.setOnClickListener(this);
    }

    private void setUpTabLayoutMain() {
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("Combo 1 người"));
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("Combo nhóm"));
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("Menu ưu đãi"));
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("Món lẻ"));

        mTabLayoutMain.setSelectedTabIndicatorColor(getResources().getColor(R.color.black));
        mTabLayoutMain.setTabTextColors(getResources().getColor(R.color.black), getResources().getColor(R.color.white));
    }

    private void setUpNavigationView() {
        mBtmNavigationView.setOnNavigationItemSelectedListener(this);
        mBtmNavigationView.setSelectedItemId(R.id.menu);
    }

    private void setUpActionBar() {
        setSupportActionBar(mToolbarMain);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setUpSliderBanner() {
        bannerMain = new ArrayList<>();
        bannerMain.add(new BannerImage("https://kfcvietnam.com.vn/uploads/banner/900f8a45e0d5c36e45ad0dcffd9e054f.png"));
        bannerMain.add(new BannerImage("https://kfcvietnam.com.vn/uploads/banner/bf8393c04ca093bf595e0af0295217b0.png"));
        bannerMain.add(new BannerImage("https://kfcvietnam.com.vn/uploads/banner/182b708be1229785fb606ac48660b852.png"));
        bannerMain.add(new BannerImage("https://kfcvietnam.com.vn/uploads/banner/7185c7da5835e592c4b86cdd4725c171.png"));

        adapter = new AdapterForSlider(this, bannerMain);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

    }

    private void setUpViewPager() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentManager fragmentManagerMain = getSupportFragmentManager();

        viewPagerAdapterForTabLine = new ViewPagerAdapterForTabLine(fragmentManager, mTabLayoutMain.getTabCount());
        viewPagerAdapterForMainTab = new ViewPagerAdapterForMainTab(fragmentManagerMain, mTabLayoutMain.getTabCount(), getApplicationContext());

        mViewPagerLine.setAdapter(viewPagerAdapterForTabLine);
        mViewPagerMain.setAdapter(viewPagerAdapterForMainTab);

        mViewPagerLine.setOffscreenPageLimit(2);
        mViewPagerMain.setOffscreenPageLimit(2);

        mViewPagerLine.setPagingEnabled(false);
        mViewPagerMain.setPagingEnabled(false);

        mViewPagerLine.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayoutMain));
    }

    private void initView() {
        sliderView             = findViewById(R.id.imageSlider);
        mToolbarMain           = findViewById(R.id.toolBarMain);
        mImgViewMore           = findViewById(R.id.imageViewMore);
        mViewPagerMain         = findViewById(R.id.viewPagerMain);
        mTabLayoutMain         = findViewById(R.id.tabLayoutMain);
        mViewPagerLine         = findViewById(R.id.viewPagerLine);
        mBtmNavigationView     = findViewById(R.id.bottomNavigationView);
        mTxtViewChangeCity     = findViewById(R.id.textViewChangeCity);
        mTxtViewCity           = findViewById(R.id.textViewCity);
        mTxtViewCartTotal      = findViewById(R.id.textViewTotalPrice);
        mTxtViewCartCount      = findViewById(R.id.textViewCartCount);
        mImgViewCart           = findViewById(R.id.imageViewCart);
        mainActivity           = findViewById(R.id.main);
        linearLayoutCart        = findViewById(R.id.linearLayoutCart);

        // Include Sign In Sign Up
        mBtnSignUp             = findViewById(R.id.buttonSignUp);
        mBtnSignIn             = findViewById(R.id.buttonSignIn);

        // Include layout of Product Added To Cart
        relativeLayoutProductAddedToCart = findViewById(R.id.includeProductAddedToCart);
        mImgButtonCancel                 = findViewById(R.id.imageButtonCancel);
        mBtnCheckOut                     = findViewById(R.id.buttonCheckOut);
        recyclerViewOrderInfo            = findViewById(R.id.recyclerViewOrderInfo);
        mProductArrList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewOrderInfo.setLayoutManager(linearLayoutManager);
        mOrderLineAdapter = new OrderLineAdapter(mProductArrList, MainActivity.this);
        recyclerViewOrderInfo.setAdapter(mOrderLineAdapter);

        // For Cart Activity
        Hawk.init(getApplicationContext()).build();
        mProductAddedToCartArrList = new ArrayList<>();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn thêm lần nữa để thoát!", Toast.LENGTH_SHORT).show();

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
