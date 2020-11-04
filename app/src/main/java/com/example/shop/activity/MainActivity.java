package com.example.shop.activity;

import com.example.shop.adapter.OrderLineAdapter;
import com.example.shop.fragment.ALaCarte_Menu_Fragment;
import com.example.shop.fragment.For_One_Fragment;
import com.example.shop.fragment.For_Sharing_Fragment;
import com.example.shop.fragment.Hot_Deals_Fragment;
import com.example.shop.fragment.Setting_Product_Fragment;
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.interfaces.OnSpinnerItemSelectedListener;
import com.example.shop.ultil.Cart;
import com.example.shop.ultil.CustomDialogStartOrdering;
import com.example.shop.ultil.Product;
import com.example.shop.ultil.CommonMethodHolder;

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
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orhanobut.hawk.Hawk;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        TabLayout.OnTabSelectedListener, View.OnClickListener, OnProductClickListener {

    Cart cart;
    TextView mTxtViewChangeCity, mTxtViewCity, mTxtViewCartTotal, mTxtViewCartCount;
    ImageView mImgButtonMore, mImgButtonHideSetting, mImgViewCart;
    Toolbar mToolbarMain;
    TabLayout mTabLayoutMain;
    noneAllowSwipeViewPager mViewPagerLine, mViewPagerMain;
    ViewPagerAdapterForTabLine viewPagerAdapterForTabLine;
    ViewPagerAdapterForMainTab viewPagerAdapterForMainTab;
    BottomNavigationView mBtmNavigationView;
    Button mBtnSignIn, mBtnSignUp;
    LinearLayout mainActivity, linearLayoutCart, mLlSettingHolder, mLlBottomNavigationHolder;
    FragmentManager fragmentManager;
    CoordinatorLayout mCoordinatorLayoutMain;

    // Include layout of Product Added To Cart
    ImageButton mImgButtonCancel;
    RecyclerView recyclerViewOrderInfo;
    Button mBtnCheckOut;
    RelativeLayout relativeLayoutProductAddedToCart;
    ArrayList<Product> mProductArrList, mProductAddedToCartArrList;
    OrderLineAdapter mOrderLineAdapter;
    boolean isShowing = false;
    boolean atSettingProduct = false;
    private int cartCount = 0;
    private int cartTotal = 0;
    private boolean cartEmpty = true;
    private int mainTabPosition;

    //For slider banner in main
    private List<BannerImage> bannerMain;
    private SliderView sliderView;
    private AdapterForSlider adapter;

    private boolean doubleBackToExitPressedOnce;
    private Handler mHandlerQuiteApp = new Handler();

    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("product/for_one_combo");

        initView();
        createCartInstance();
//        Hawk.deleteAll();
        setUpActionBar();
        setUpTabLayoutMain();
        setViewOnClick();
        //If create not from edit product
        if (!cart.isRequireFromEditProduct()) {
            setUpSliderBanner();
            setUpNavigationView();
            setUpViewPagerALaCarte();
            setUpViewPagerCombo();
            if (Hawk.get("reCreateFromTab") != null) {
                boolean reCreateFromTab = Hawk.get("reCreateFromTab");
                if (reCreateFromTab) {
                    Hawk.put("reCreateFromTab", false);
                    cartEmpty = false;
                    mainTabPosition = Hawk.get("mainTabPosition");
                    mViewPagerMain.setCurrentItem(mainTabPosition);
                    mTabLayoutMain.getTabAt(mainTabPosition).select();

                }
            }
            setUpCart();
        }
        // If main is create from edit product
        else {
            // get the last position of tab
            if (Hawk.get("mainTabPosition") != null) {
                mainTabPosition = Hawk.get("mainTabPosition");
                mViewPagerMain.setCurrentItem(mainTabPosition);
                mTabLayoutMain.getTabAt(mainTabPosition).select();
            }
            setUpCart();
            showSettingProduct();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mainTabPosition = tab.getPosition();
        Hawk.put("mainTabPosition", mainTabPosition);
        mViewPagerLine.setCurrentItem(tab.getPosition());
        mViewPagerMain.setCurrentItem(tab.getPosition());
        if (atSettingProduct) {
            if (cart.isRequireFromEditProduct()) {
                Hawk.put("reCreateFromTab", true);
                CommonMethodHolder.saveCart(mProductAddedToCartArrList, cartCount,
                        mTxtViewCartTotal.getText().toString(), false, cart);
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.finish();
                startActivity(intent);
            } else {
                hideSettingProductFragment();
            }
        }
    }

    private void setUpCart() {
        mProductAddedToCartArrList.clear();
        if (cart.getCartCount() != 0) {
            cartEmpty = false;
            cartCount = cart.getCartCount();
            mProductAddedToCartArrList.addAll(cart.getArrListProductInCart());
            mTxtViewCartCount.setTextColor(getResources().getColor(R.color.white));
            mImgViewCart.setImageDrawable(getResources().getDrawable(R.drawable.cart_icon_active));
            mTxtViewCartCount.setText(cartCount + "");
            String cartTotalText = cart.getCartTotal();
            mTxtViewCartTotal.setText(cartTotalText);
            cartTotal = CommonMethodHolder.convertStringToInt(cartTotalText);
        }
        if (cart.getCartCount() == 0) {
            mTxtViewCartCount.setTextColor(getResources().getColor(R.color.black));
            mImgViewCart.setImageDrawable(getResources().getDrawable(R.drawable.cart_icon));
            mTxtViewCartTotal.setText("Giỏ hàng");
            mTxtViewCartCount.setText("0");
            cartEmpty = true;
        }
    }

    @Override
    public void onSettingProduct(Product product) {
        cart.setRequireFromEditProduct(false);
        showSettingProduct();
    }

    @Override
    public void onOrderProduct(Product product) {
        addSingleToCart(product);
        showSingleProductAddedToCart(product);
    }

    private void showSettingProduct() {
        Setting_Product_Fragment setting_product_fragment = new Setting_Product_Fragment(cart.isRequireFromEditProduct());
        mLlBottomNavigationHolder.setVisibility(View.INVISIBLE);
        mLlSettingHolder.setVisibility(View.VISIBLE);
        mCoordinatorLayoutMain.setVisibility(View.INVISIBLE);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.linearLayoutSettingHolder, setting_product_fragment).commit();
        ft.addToBackStack("Setting Product");
        atSettingProduct = true;
        mImgButtonMore.setVisibility(View.GONE);
        mImgButtonHideSetting.setVisibility(View.VISIBLE);
        setting_product_fragment.setOnButtonClickListener(new Setting_Product_Fragment.OnButtonClickListener() {
            @Override
            public void onHideSettingProductFragment() {
                hideSettingProductFragment();
            }

            @Override
            public void onAddToCart(ArrayList<Product> addToCartList) {
                if(addToCartList.size() <= 1){
                    addSingleToCart(addToCartList.get(0));
                    showSingleProductAddedToCart(addToCartList.get(0));
                }else{
                    addMultipleToCart(addToCartList);
                    showListProductAddedToCart(addToCartList);
                }

            }

            @Override
            public void onReturnCart() {
                finish();
            }

            @Override
            public void onUpdateCart(Product product) {
                for(int i = 0; i < mProductAddedToCartArrList.size(); i++){
                    Product productTemp = mProductAddedToCartArrList.get(i);
                    if(productTemp.getFoodName().equals(product.getFoodName())){
                        if(productTemp.getPortion() > product.getPortion()){
                            int minusPortionCount = productTemp.getPortion() - product.getPortion();
                            cartCount = cartCount - minusPortionCount;
                            cartTotal = cartTotal - minusPortionCount * CommonMethodHolder.convertStringToInt(product.getFoodPrice());
                        }else if(productTemp.getPortion() < product.getPortion()){
                            int plusPortionCount = product.getPortion() - productTemp.getPortion();
                            cartCount = cartCount + plusPortionCount;
                            cartTotal = cartTotal + plusPortionCount * CommonMethodHolder.convertStringToInt(product.getFoodPrice());
                        }else{
                            finish();
                        }
                        mProductAddedToCartArrList.get(i).setPortion(product.getPortion());
                        CommonMethodHolder.saveCart(mProductAddedToCartArrList, cartCount, CommonMethodHolder.convertIntToString(cartTotal), false, cart);
                        finish();
                    }
                }

            }
        });
    }

    private void showListProductAddedToCart(ArrayList<Product> arrListProductAddedToCart){
        ShowLayoutProductAddToCart();
        mProductArrList.addAll(arrListProductAddedToCart);
        mProductAddedToCartArrList.addAll(arrListProductAddedToCart);
    }

    private void showSingleProductAddedToCart(Product product) {
        ShowLayoutProductAddToCart();
        mProductArrList.add(product);
        mOrderLineAdapter.notifyDataSetChanged();
        mProductAddedToCartArrList.add(product);
    }

    private void ShowLayoutProductAddToCart(){
        relativeLayoutProductAddedToCart.setVisibility(View.VISIBLE);
        relativeLayoutProductAddedToCart.bringToFront();
        relativeLayoutProductAddedToCart.setElevation(getResources().getDimension(R.dimen._4sdp));
        if (mProductArrList != null) {
            mProductArrList.clear();
        }
        mOrderLineAdapter.notifyDataSetChanged();
        isShowing = true;
    }

    private void addMultipleToCart(ArrayList<Product> arrListAddToCart){
        int total = 0, portion = 0;
        for(int i =0; i< arrListAddToCart.size(); i++){
            Product product = arrListAddToCart.get(i);
            total = total + product.getPortion() * CommonMethodHolder.convertStringToInt(product.getFoodPrice());
            portion = portion + product.getPortion();
        }
       addCart(total, portion);
    }

    private void addCart(int total, int portion){

        if (cartEmpty) {
            cartCount = portion;
            mTxtViewCartTotal.setText(CommonMethodHolder.convertIntToString(total));
            mTxtViewCartCount.setText((cartCount + ""));
            mTxtViewCartCount.setTextColor(getResources().getColor(R.color.white));
            mImgViewCart.setImageDrawable(getResources().getDrawable(R.drawable.cart_icon_active));
            cartTotal = total;
            cartEmpty = false;
        } else {
            cartTotal = cartTotal + total;
            cartCount = cartCount + portion;
            mTxtViewCartCount.setText((cartCount + ""));
            mTxtViewCartTotal.setText(CommonMethodHolder.convertIntToString(cartTotal));
        }

    }


    private void addSingleToCart(Product product) {
        int total = CommonMethodHolder.convertStringToInt(product.getFoodPrice())*product.getPortion();
        addCart(total, product.getPortion());
    }

    private void setViewOnClick() {
        mImgButtonMore.setOnClickListener(this);
        mImgButtonHideSetting.setOnClickListener(this);
        mBtnSignUp.setOnClickListener(this);
        mTxtViewChangeCity.setOnClickListener(this);
        mTabLayoutMain.addOnTabSelectedListener(this);
        mImgButtonCancel.setOnClickListener(this);
        linearLayoutCart.setOnClickListener(this);
    }

    private void initView() {
        sliderView = findViewById(R.id.imageSlider);
        mToolbarMain = findViewById(R.id.toolBarMain);
        mImgButtonMore = findViewById(R.id.imageButtonMore);
        mImgButtonHideSetting = findViewById(R.id.imageButtonHideSetting);
        mViewPagerMain = findViewById(R.id.viewPagerMain);
        mTabLayoutMain = findViewById(R.id.tabLayoutMain);
        mViewPagerLine = findViewById(R.id.viewPagerLine);
        mBtmNavigationView = findViewById(R.id.bottomNavigationView);
        mTxtViewChangeCity = findViewById(R.id.textViewChangeCity);
        mTxtViewCity = findViewById(R.id.textViewCity);
        mTxtViewCartTotal = findViewById(R.id.textViewTotalPrice);
        mTxtViewCartCount = findViewById(R.id.textViewCartCount);
        mImgViewCart = findViewById(R.id.imageViewCart);
        mainActivity = findViewById(R.id.main);
        linearLayoutCart = findViewById(R.id.linearLayoutCart);
        mLlSettingHolder = findViewById(R.id.linearLayoutSettingHolder);
        mLlBottomNavigationHolder = findViewById(R.id.bottomNavigationHolder);
        mCoordinatorLayoutMain = findViewById(R.id.coordinatorLayoutMain);
        fragmentManager = getSupportFragmentManager();

        // Include Sign In Sign Up
        mBtnSignUp = findViewById(R.id.buttonSignUp);
        mBtnSignIn = findViewById(R.id.buttonSignIn);

        // Include layout of Product Added To Cart
        relativeLayoutProductAddedToCart = findViewById(R.id.includeProductAddedToCart);
        mImgButtonCancel = findViewById(R.id.imageButtonCancel);
        mBtnCheckOut = findViewById(R.id.buttonCheckOut);
        recyclerViewOrderInfo = findViewById(R.id.recyclerViewOrderInfo);
        mProductArrList = new ArrayList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewOrderInfo.setLayoutManager(linearLayoutManager);
        mOrderLineAdapter = new OrderLineAdapter(mProductArrList, MainActivity.this);
        recyclerViewOrderInfo.setAdapter(mOrderLineAdapter);

        // For Cart Activity
        Hawk.init(getApplicationContext()).build();
        mProductAddedToCartArrList = new ArrayList();

    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        getShowingFragment(fragment);
    }

    private void getShowingFragment(Fragment fragment) {
        if (fragment instanceof For_One_Fragment) {
            For_One_Fragment for_one = (For_One_Fragment) fragment;
            for_one.setProductClickListener(this);
        } else if (fragment instanceof For_Sharing_Fragment) {
            For_Sharing_Fragment for_sharing = (For_Sharing_Fragment) fragment;
            for_sharing.setProductClickListener(this);
        } else if (fragment instanceof Hot_Deals_Fragment) {
            Hot_Deals_Fragment hot_deals = (Hot_Deals_Fragment) fragment;
            hot_deals.setProductClickListener(this);
        } else if (fragment instanceof ALaCarte_Menu_Fragment) {
            ALaCarte_Menu_Fragment aLaCarte_menu = (ALaCarte_Menu_Fragment) fragment;
            aLaCarte_menu.setOnProductClickListener(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (atSettingProduct) {
            if (cart.isRequireFromEditProduct()) {
                CommonMethodHolder.saveCart(mProductAddedToCartArrList, cartCount, mTxtViewCartTotal.getText().toString(), false, cart);
                finish();
            } else {
                hideSettingProductFragment();
            }
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Nhấn thêm lần nữa để thoát!", Toast.LENGTH_SHORT).show();

            mHandlerQuiteApp.postDelayed(mRunnable, 2000);
        }
    }

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonMore:
                Intent intentMore = new Intent(MainActivity.this, MoreActivity.class);
                startActivity(intentMore);
                break;
            case R.id.imageButtonHideSetting:
                if(cart.isRequireFromEditProduct()){
                    finish();
                }else{
                    hideSettingProductFragment();
                }
                break;
            case R.id.buttonSignUp:
                Intent intentSignUp = new Intent(MainActivity.this, AccountRegisterActivity.class);
                startActivity(intentSignUp);
                break;
            case R.id.textViewChangeCity:
                final String[] cityList = getResources().getStringArray(R.array.city);
                final CustomDialogStartOrdering customDialogStartOrdering = new CustomDialogStartOrdering(MainActivity.this);
                customDialogStartOrdering.setCancelable(false);
                customDialogStartOrdering.setOnItemSelectedListener(new OnSpinnerItemSelectedListener() {
                    @Override
                    public void onItemSelected(int position) {
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
                Log.d("KKK", "" + mProductAddedToCartArrList.size());
                CommonMethodHolder.saveCart(mProductAddedToCartArrList, cartCount,
                        mTxtViewCartTotal.getText().toString(), false, cart);
                Intent intentCart = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intentCart);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
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

    private void setUpViewPagerALaCarte() {
        viewPagerAdapterForTabLine = new ViewPagerAdapterForTabLine(fragmentManager, mTabLayoutMain.getTabCount());
        mViewPagerLine.setAdapter(viewPagerAdapterForTabLine);
        mViewPagerLine.setOffscreenPageLimit(2);
        mViewPagerLine.setPagingEnabled(false);
        mViewPagerLine.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayoutMain));
    }

    private void setUpViewPagerCombo() {
        viewPagerAdapterForMainTab = new ViewPagerAdapterForMainTab(fragmentManager, mTabLayoutMain.getTabCount(), getApplicationContext());
        mViewPagerMain.setAdapter(viewPagerAdapterForMainTab);
        mViewPagerMain.setOffscreenPageLimit(2);
        mViewPagerMain.setPagingEnabled(false);
    }

    private void hideSettingProductFragment() {
        fragmentManager.popBackStack();
        atSettingProduct = false;
        mImgButtonHideSetting.setVisibility(View.GONE);
        mImgButtonMore.setVisibility(View.VISIBLE);
        mCoordinatorLayoutMain.setVisibility(View.VISIBLE);
        mLlBottomNavigationHolder.setVisibility(View.VISIBLE);
    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    private void createCartInstance() {
        if (Hawk.get("cart") == null) {
            cart = Cart.getInstance();
            cart.setRequireFromEditProduct(false);
            Hawk.put("cart", cart);
        } else {
            cart = Hawk.get("cart");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cart.isRequireFromEditProduct()) {
            CommonMethodHolder.saveCart(mProductAddedToCartArrList, cartCount,
                    mTxtViewCartTotal.getText().toString(), false, cart);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        CommonMethodHolder.saveCart(mProductAddedToCartArrList, cartCount,
                mTxtViewCartTotal.getText().toString(), false, cart);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Check if the cart has been change and set up cart layout
        cart = Hawk.get("cart");
        if (cart != null) {
            setUpCart();
            if(Hawk.get("isKeepShopping")!=null){
                boolean isKeepShopping = Hawk.get("isKeepShopping");
                if(isKeepShopping && atSettingProduct){
                    hideSettingProductFragment();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CommonMethodHolder.saveCart(mProductAddedToCartArrList, cartCount,
                mTxtViewCartTotal.getText().toString() , cart.isRequireFromEditProduct(), cart);
        if (mHandlerQuiteApp != null) {
            mHandlerQuiteApp.removeCallbacks(mRunnable);
        }
    }
}
