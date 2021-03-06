package com.example.shop.activity;

import com.example.shop.adapter.OrderLineAdapter;
import com.example.shop.broadcasts.NetworkBroadcastReceiver;
import com.example.shop.dialogs.CustomDialogLoading;
import com.example.shop.dialogs.CustomDialogNoInternet;
import com.example.shop.fragment.ALaCarteMenuFragment;
import com.example.shop.fragment.ForOneFragment;
import com.example.shop.fragment.ForSharingFragment;
import com.example.shop.fragment.HotDealsFragment;
import com.example.shop.fragment.SettingProductFragment;
import com.example.shop.interfaces.OnFragmentScrollListener;
import com.example.shop.interfaces.OnLoadDataListener;
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.interfaces.OnSpinnerItemSelectedListener;
import com.example.shop.objects.Cart;
import com.example.shop.dialogs.CustomDialogStartOrdering;
import com.example.shop.objects.Customer;
import com.example.shop.objects.Product;
import com.example.shop.utils.CommonMethodHolder;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.adapter.SliderAdapter;
import com.example.shop.adapter.ViewPagerAdapterForTabLine;
import com.example.shop.adapter.ViewPagerAdapterForMainTab;
import com.example.shop.R;
import com.example.shop.objects.Promotion;
import com.example.shop.utils.NoneAllowSwipeViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        TabLayout.OnTabSelectedListener, View.OnClickListener, OnProductClickListener, OnFragmentScrollListener {

    // Showing layout base on Sign In status
    private final DatabaseReference mDataRef = FirebaseDatabase.getInstance().getReference();
    private LinearLayout mLlSignedUp, mLlSignInAndSignUp;
    private TextView mTxtViewUserName;
    private Button mBtnSignIn, mBtnSignUp, mBtnOrderHistory;
    private final int REQUEST_CODE_FROM_MAIN = 111;

    private Cart cart;
    private TextView mTxtViewChangeCity, mTxtViewCity, mTxtViewCartTotal, mTxtViewCartCount;
    private ImageView mImgButtonMore, mImgButtonHideSetting, mImgViewCart;
    private Toolbar mToolbarMain;
    private TabLayout mTabLayoutMain;
    private NoneAllowSwipeViewPager mViewPagerLine, mViewPagerMain;
    private ViewPagerAdapterForMainTab viewPagerAdapterForMainTab;
    private BottomNavigationView mBtmNavigationView;

    private LinearLayout linearLayoutCart, mLlSettingHolder, mLlBottomNavigationHolder;
    private FragmentManager fragmentManager;
    private CoordinatorLayout mCoordinatorLayoutMain;

    // Include layout of Product Added To Cart
    private ImageButton mImgButtonCancel;
    private RecyclerView recyclerViewOrderInfo;
    private Button mBtnCheckOut;
    private RelativeLayout relativeLayoutProductAddedToCart;
    private ArrayList<Product> mProductArrList, mProductAddedToCartArrList;
    private OrderLineAdapter mOrderLineAdapter;
    private boolean isShowing = false;
    private boolean atSettingProduct = false;
    private int cartCount = 0;
    private int cartTotal = 0;
    private boolean cartEmpty = true;
    private int mainTabPosition;

    //For slider banner in main
    private ArrayList<String> bannerMain;
    private ArrayList<Promotion> promotionList;
    private SliderView sliderView;
    private SliderAdapter adapter;

    private boolean doubleBackToExitPressedOnce;
    private final Handler mHandlerQuiteApp = new Handler();
    private CustomDialogLoading dialogLoading;

    private FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        createCartInstance();
        setUpActionBar();
        setUpTabLayoutMain();
        setViewOnClick();

//        Hawk.deleteAll();

        //If create not from edit product
        if (!cart.isRequireFromEditProduct()) {
            showLoadingDialog();
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
        }
        // If main is create from edit product
        else {
            // get the last position of tab
            if (Hawk.get("mainTabPosition") != null) {
                mainTabPosition = Hawk.get("mainTabPosition");
                mViewPagerMain.setCurrentItem(mainTabPosition);
                mTabLayoutMain.getTabAt(mainTabPosition).select();
            }
            showSettingProduct();
        }
        setUpCart();

    }

    private void showLoadingDialog() {
        dialogLoading = new CustomDialogLoading();
        dialogLoading.setCancelable(false);
        dialogLoading.show(fragmentManager, "Loading Dialog");
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
            mImgViewCart.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.cart_icon_active, null));
            mTxtViewCartCount.setText((cartCount + ""));
            String cartTotalText = cart.getCartTotal();
            mTxtViewCartTotal.setText(cartTotalText);
            cartTotal = CommonMethodHolder.convertStringToInt(cartTotalText);
        }
        if (cart.getCartCount() == 0) {
            mTxtViewCartCount.setTextColor(getResources().getColor(R.color.black));
            mImgViewCart.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.cart_icon, null));
            mTxtViewCartTotal.setText(getResources().getString(R.string.cart));
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
        SettingProductFragment setting_product_fragment = new SettingProductFragment(cart.isRequireFromEditProduct());
        mLlBottomNavigationHolder.setVisibility(View.GONE);
        mLlSettingHolder.setVisibility(View.VISIBLE);
        mCoordinatorLayoutMain.setVisibility(View.GONE);

        ft.add(R.id.linearLayoutSettingHolder, setting_product_fragment).commit();
        ft.addToBackStack("Setting Product");
        atSettingProduct = true;
        mImgButtonMore.setVisibility(View.GONE);
        mImgButtonHideSetting.setVisibility(View.VISIBLE);
        setting_product_fragment.setOnButtonClickListener(new SettingProductFragment.OnButtonClickListener() {
            @Override
            public void onHideSettingProductFragment() {
                hideSettingProductFragment();
            }

            @Override
            public void onAddToCart(ArrayList<Product> addToCartList) {

                if (addToCartList.size() <= 1) {
                    addSingleToCart(addToCartList.get(0));
                    showSingleProductAddedToCart(addToCartList.get(0));
                } else {
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
                Log.d("DDD", "At Main Update Cart: " + product.getAlacarte().get(0).getChosen_alacarte());

                for (int i = 0; i < mProductAddedToCartArrList.size(); i++) {
                    Product productTemp = mProductAddedToCartArrList.get(i);
                    if (productTemp.getFood_name().equals(product.getFood_name())) {
                        // Check if product portion has been change
                        if (productTemp.getPortion() > product.getPortion()) {
                            int minusPortionCount = productTemp.getPortion() - product.getPortion();
                            cartCount = cartCount - minusPortionCount;
                            cartTotal = cartTotal - minusPortionCount * CommonMethodHolder.convertStringToInt(product.getFood_price());
                        } else if (productTemp.getPortion() < product.getPortion()) {
                            int plusPortionCount = product.getPortion() - productTemp.getPortion();
                            cartCount = cartCount + plusPortionCount;
                            cartTotal = cartTotal + plusPortionCount * CommonMethodHolder.convertStringToInt(product.getFood_price());
                        }

                        // Check if product alacarte has been upgrade or downgrade
                        if (!productTemp.getFood_price().equals(product.getFood_price())) {

                            // Save price change to cart
                            int lastPrice = CommonMethodHolder.convertStringToInt(productTemp.getFood_price());
                            int currentPrice = CommonMethodHolder.convertStringToInt(product.getFood_price());
                            if (lastPrice > currentPrice) {
                                cartTotal = cartTotal - (lastPrice - currentPrice);
                            } else if (lastPrice < currentPrice) {
                                cartTotal = cartTotal + (currentPrice - lastPrice);
                            }

                            // Save change to product
                            mProductAddedToCartArrList.get(i).setFood_price(product.getFood_price());
                            mProductAddedToCartArrList.get(i).setAlacarte(product.getAlacarte());
                        }

                        // Check if alacarte option has been change
                        for (int j = 0; j < productTemp.getAlacarte().size(); j++) {
                            Log.d("DDD", "j: " + j + "; alacarte size: " + productTemp.getAlacarte().size());
                            if (!productTemp.getAlacarte().get(j).isUpgradable()) {
                                if (!productTemp.getAlacarte().get(j).getChosen_alacarte().trim()
                                        .equals(product.getAlacarte().get(j).getChosen_alacarte().trim())) {
                                    mProductAddedToCartArrList.get(i).getAlacarte().get(j).setChosen_alacarte(product.getAlacarte().get(j).getChosen_alacarte());
                                }
                            }
                        }
                        mProductAddedToCartArrList.get(i).setPortion(product.getPortion());
                        CommonMethodHolder.saveCart(mProductAddedToCartArrList, cartCount, CommonMethodHolder.convertIntToString(cartTotal), false, cart);
                        finish();
                    }
                }

            }
        });
    }

    private void addSingleToCart(Product product) {
        int total = CommonMethodHolder.convertStringToInt(product.getFood_price()) * product.getPortion();
        addCart(total, product.getPortion());
    }

    private void showSingleProductAddedToCart(Product product) {
        ShowLayoutProductAddToCart();
        mProductArrList.add(product);
        mOrderLineAdapter.notifyDataSetChanged();
        mProductAddedToCartArrList.add(product);
    }


    private void addCart(int total, int portion) {

        if (cartEmpty) {
            cartCount = portion;
            mTxtViewCartTotal.setText(CommonMethodHolder.convertIntToString(total));
            mTxtViewCartCount.setText((cartCount + ""));
            mTxtViewCartCount.setTextColor(getResources().getColor(R.color.white));
            mImgViewCart.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.cart_icon_active, null));
            cartTotal = total;
            cartEmpty = false;
        } else {
            cartTotal = cartTotal + total;
            cartCount = cartCount + portion;
            mTxtViewCartCount.setText((cartCount + ""));
            mTxtViewCartTotal.setText(CommonMethodHolder.convertIntToString(cartTotal));
        }

    }

    private void showListProductAddedToCart(ArrayList<Product> arrListProductAddedToCart) {
        ShowLayoutProductAddToCart();
        mProductArrList.addAll(arrListProductAddedToCart);
        mProductAddedToCartArrList.addAll(arrListProductAddedToCart);
    }


    private void ShowLayoutProductAddToCart() {
        relativeLayoutProductAddedToCart.setVisibility(View.VISIBLE);
        relativeLayoutProductAddedToCart.bringToFront();
        relativeLayoutProductAddedToCart.setElevation(getResources().getDimension(R.dimen._4sdp));
        if (mProductArrList != null) {
            mProductArrList.clear();
        }
        mOrderLineAdapter.notifyDataSetChanged();
        isShowing = true;
    }

    private void addMultipleToCart(ArrayList<Product> arrListAddToCart) {
        int total = 0, portion = 0;
        for (int i = 0; i < arrListAddToCart.size(); i++) {
            Product product = arrListAddToCart.get(i);
            total = total + product.getPortion() * CommonMethodHolder.convertStringToInt(product.getFood_price());
            portion = portion + product.getPortion();
        }
        addCart(total, portion);
    }

    private void setViewOnClick() {
        mImgButtonMore.setOnClickListener(this);
        mImgButtonHideSetting.setOnClickListener(this);
        mBtnSignUp.setOnClickListener(this);
        mBtnSignIn.setOnClickListener(this);
        mTxtViewChangeCity.setOnClickListener(this);
        mTabLayoutMain.addOnTabSelectedListener(this);
        mImgButtonCancel.setOnClickListener(this);
        linearLayoutCart.setOnClickListener(this);
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

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
        linearLayoutCart = findViewById(R.id.linearLayoutCart);
        mLlSettingHolder = findViewById(R.id.linearLayoutSettingHolder);
        mLlBottomNavigationHolder = findViewById(R.id.bottomNavigationHolder);
        mCoordinatorLayoutMain = findViewById(R.id.coordinatorLayoutMain);

        // Include Sign In Sign Up
        mLlSignedUp = findViewById(R.id.linearLayoutSignedUp);
        mLlSignInAndSignUp = findViewById(R.id.linearLayoutSignInAndSignUp);
        mBtnSignUp = findViewById(R.id.buttonSignUp);
        mBtnSignIn = findViewById(R.id.buttonSignIn);
        mBtnOrderHistory = findViewById(R.id.buttonOrderHistory);
        mTxtViewUserName = findViewById(R.id.textViewUserName);

        // Include layout of Product Added To Cart
        relativeLayoutProductAddedToCart = findViewById(R.id.includeProductAddedToCart);
        mImgButtonCancel = findViewById(R.id.imageButtonCancel);
        mBtnCheckOut = findViewById(R.id.buttonCheckOut);
        recyclerViewOrderInfo = findViewById(R.id.recyclerViewOrderInfo);
        mProductArrList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewOrderInfo.setLayoutManager(linearLayoutManager);
        mOrderLineAdapter = new OrderLineAdapter(mProductArrList, MainActivity.this);
        recyclerViewOrderInfo.setAdapter(mOrderLineAdapter);

        // For Cart Activity
        Hawk.init(getApplicationContext()).build();
        mProductAddedToCartArrList = new ArrayList<>();

        NetworkBroadcastReceiver networkBroadcastReceiver = new NetworkBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(networkBroadcastReceiver, intentFilter);
        networkBroadcastReceiver.setOnNetworkChangeListener(new NetworkBroadcastReceiver.OnNetworkChangeListener() {
            @Override
            public void onNetworkChange(boolean hasNetwork) {
                if (!hasNetwork) {
                    CustomDialogNoInternet dialogNoInternet = new CustomDialogNoInternet();
                    dialogNoInternet.show(getSupportFragmentManager(), "No Internet Dialog");
                }
            }
        });


    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        getShowingFragment(fragment);
    }

    private void getShowingFragment(Fragment fragment) {
        if (fragment instanceof ForOneFragment) {
            ForOneFragment for_one = (ForOneFragment) fragment;
            for_one.setProductClickListener(this);
            for_one.setOnFragmentScrollListener(this);
            for_one.setOnLoadDataListener(new OnLoadDataListener() {
                @Override
                public void onSucceed() {
                    mViewPagerLine.setVisibility(View.VISIBLE);
                    dialogLoading.dismiss();
                }

                @Override
                public void onFailed() {

                }
            });
        } else if (fragment instanceof ForSharingFragment) {
            ForSharingFragment for_sharing = (ForSharingFragment) fragment;
            for_sharing.setProductClickListener(this);
            for_sharing.setOnFragmentScrollListener(this);
        } else if (fragment instanceof HotDealsFragment) {
            HotDealsFragment hot_deals = (HotDealsFragment) fragment;
            hot_deals.setProductClickListener(this);
            hot_deals.setOnFragmentScrollListener(this);
        } else if (fragment instanceof ALaCarteMenuFragment) {
            ALaCarteMenuFragment aLaCarte_menu = (ALaCarteMenuFragment) fragment;
            aLaCarte_menu.setOnProductClickListener(this);
            aLaCarte_menu.setOnFragmentScrollListener(this);
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
                if (cart.isRequireFromEditProduct()) {
                    finish();
                } else {
                    hideSettingProductFragment();
                }
                break;
            case R.id.buttonSignUp:
                Intent intentSignUp = new Intent(MainActivity.this, AccountRegisterActivity.class);
                startActivityForResult(intentSignUp, REQUEST_CODE_FROM_MAIN);
                break;
            case R.id.buttonSignIn:
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
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
        promotionList = new ArrayList<>();
        adapter = new SliderAdapter(this, bannerMain);
        adapter.setOnBannerClickListener(new SliderAdapter.OnBannerClickListener() {
            @Override
            public void onClick(int position) {
                String newsUrl = promotionList.get(position).getLink();
                Intent intentViewPromotionNews = new Intent(MainActivity.this, ViewPromotionActivity.class);
                intentViewPromotionNews.putExtra("newsUrl", newsUrl);
                startActivity(intentViewPromotionNews);
            }
        });

        mDataRef.child("promotion_news").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                bannerMain.add(snapshot.getValue(Promotion.class).getBanner());
                promotionList.add(snapshot.getValue(Promotion.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }

    private void setUpViewPagerALaCarte() {
        ViewPagerAdapterForTabLine viewPagerAdapterForTabLine = new ViewPagerAdapterForTabLine(fragmentManager, mTabLayoutMain.getTabCount());
        mViewPagerLine.setAdapter(viewPagerAdapterForTabLine);
        mViewPagerLine.setOffscreenPageLimit(2);
        mViewPagerLine.setPagingEnabled(false);
        mViewPagerLine.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayoutMain));
    }

    private void setUpViewPagerCombo() {
        viewPagerAdapterForMainTab = new ViewPagerAdapterForMainTab(fragmentManager, mTabLayoutMain.getTabCount(), getApplicationContext());
        mViewPagerMain.setAdapter(viewPagerAdapterForMainTab);
        mViewPagerMain.setOffscreenPageLimit(3);
        mViewPagerMain.setPagingEnabled (false);
    }

    private void hideSettingProductFragment() {
        try {
            fragmentManager.popBackStackImmediate();
        }catch (Exception e){
            Log.d("EEE", "Error: " + e.getMessage());
        }
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
            Log.d("DDD", "In Main Cart Null");
            cart = Cart.getInstance();
            cart.setRequireFromEditProduct(false);
            Hawk.put("cart", cart);
        } else {
            Log.d("DDD", "In Main Cart Not Null");
            cart = Hawk.get("cart");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FROM_MAIN && resultCode == RESULT_OK && data != null) {
            String customerJson = data.getStringExtra("customer");
            Gson gson = new Gson();
            Customer customer = gson.fromJson(customerJson, Customer.class);
            mTxtViewUserName.setText(customer.getFull_name());
            mLlSignedUp.setVisibility(View.VISIBLE);
            mLlSignInAndSignUp.setVisibility(View.GONE);
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
    protected void onStart() {
        super.onStart();
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            mLlSignInAndSignUp.setVisibility(View.GONE);
            mLlSignedUp.setVisibility(View.VISIBLE);

            mDataRef.child("user").child(currentUser.getUid()).child("full_name").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mTxtViewUserName.setText(snapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            mBtnOrderHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, OrderHistoryActivity.class));
                }
            });
        } else {
            mLlSignedUp.setVisibility(View.GONE);
            mLlSignInAndSignUp.setVisibility(View.VISIBLE);
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
        // Hide the Layout Product Added To Cart if it is showing
        if (isShowing) {
            relativeLayoutProductAddedToCart.setVisibility(View.GONE);
            isShowing = false;
        }

        // Check if the cart has been change and set up cart layout
        cart = Hawk.get("cart");
        if (cart != null) {
            setUpCart();
            if (Hawk.get("isKeepShopping") != null) {
                boolean isKeepShopping = Hawk.get("isKeepShopping");
                if (isKeepShopping && atSettingProduct) {
                    hideSettingProductFragment();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Hawk.get("orderSuccessful") == null) {
            Hawk.put("orderSuccessful", false);
        }
        boolean orderSuccessful = Hawk.get("orderSuccessful");
        if (orderSuccessful) {

        } else {
            CommonMethodHolder.saveCart(mProductAddedToCartArrList, cartCount,
                    mTxtViewCartTotal.getText().toString(), cart.isRequireFromEditProduct(), cart);
        }
        if (mHandlerQuiteApp != null) {
            mHandlerQuiteApp.removeCallbacks(mRunnable);
        }
    }

    @Override
    public void onScroll() {
        if (isShowing) {
            relativeLayoutProductAddedToCart.setVisibility(View.GONE);
            isShowing = false;
        }
    }
}
