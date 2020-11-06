package com.example.shop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.adapter.AdapterForSlider;
import com.example.shop.adapter.AddsOnAdapter;
import com.example.shop.adapter.SeperateProductAdapter;
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.ultil.Product;
import com.example.shop.ultil.ProductSeperated;
import com.example.shop.ultil.CommonMethodHolder;
import com.orhanobut.hawk.Hawk;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Setting_Product_Fragment extends Fragment implements View.OnClickListener{

    // Product Setting
    TextView mTxtViewProductName, mTxtViewProductPrice, mTxtViewProductPortion, mTxtViewProductTotalPrice;
    ImageButton mImgButtonProductMinus, mImgButtonProductPlus;
    Button mBtnReturnMenu, mBtnAddToCart, mBtnReturnCart, mBtnUpdateCart;
    LinearLayoutManager mLLManagerSeperateProduct;
    SeperateProductAdapter seperateProductAdapter;
    RecyclerView mRclViewProductInfo;
    AdapterForSlider mAdapterSlider;
    SliderView mSliderView;
    ArrayList<ProductSeperated> mArrListName;
    Product productSetting;
    OnButtonClickListener onButtonClickListener;
    LinearLayout mLLFromMain, mLLFromCart;
    boolean fromCart;
    int grandTotal, totalProductPrice, totalAddsOnPrice = 0, totalGoesGreateWithPrice = 0;

    //Layout holder Goes Greate With and Adds On
    LinearLayout mLLAddsOnAndGoesGreateWith;

    // Goes Greate With
    TextView mTxtViewGoesGreateWithName, mTxtViewGoesGreateWithPrice, mTxtViewGoesGreateWithTotalPrice, mTxtViewGoesGreateWithPortion;
    ImageButton mImgButtonGoesGreateWithMinus, mImgButtonGoesGreateWithPlus;
    Product productGoesGreateWith;
    ArrayList<String> ProductGoesGreateWithBanner;

    // Adds On
    LinearLayoutManager mLLManagerAddsOn;
    ArrayList<Product> mArrListAddsOn;
    AddsOnAdapter addsOnAdapter;
    RecyclerView mRclViewAddsOn;
    ArrayList<String> FriesAndPepsiAddsOnBanner, CheeseAddsOnBanner;
    OnProductClickListener onProductClickListener;

    ArrayList<Product> mProductAddsOnForAddingToCartList;
    public Setting_Product_Fragment() {
        // Required empty public constructor
    }

    public Setting_Product_Fragment(boolean fromCart) {
        this.fromCart = fromCart;
    }

    public void setOnProductClickListener(OnProductClickListener onProductClickListener){
        this.onProductClickListener = onProductClickListener;
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener){
        this.onButtonClickListener = onButtonClickListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_product, container, false);

        initView(view);
        settingLayout();
        setUpViewClick();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonProductMinus:
                if (productSetting.getPortion() > 1) {
                    productSetting.setPortion(productSetting.getPortion() - 1);
                    mTxtViewProductPortion.setText((productSetting.getPortion() + ""));
                    totalProductPrice = totalProductPrice - CommonMethodHolder.convertStringToInt(productSetting.getFood_price());
                    mTxtViewProductTotalPrice.setText(CommonMethodHolder.convertIntToString(totalProductPrice));
                    updateButton();
                }
                break;
            case R.id.imageButtonProductPlus:
                productSetting.setPortion(productSetting.getPortion() + 1);
                mTxtViewProductPortion.setText((productSetting.getPortion() + ""));
                totalProductPrice = totalProductPrice + CommonMethodHolder.convertStringToInt(productSetting.getFood_price());
                mTxtViewProductTotalPrice.setText(CommonMethodHolder.convertIntToString(totalProductPrice));
                updateButton();
                break;
            case R.id.imageButtonAddsOnMinus:
                if (productGoesGreateWith.getPortion() > 0) {
                    productGoesGreateWith.setPortion(productGoesGreateWith.getPortion() - 1);
                    mTxtViewGoesGreateWithPortion.setText((productGoesGreateWith.getPortion() + ""));
                    totalGoesGreateWithPrice = totalGoesGreateWithPrice - CommonMethodHolder.convertStringToInt(productGoesGreateWith.getFood_price());
                    mTxtViewGoesGreateWithTotalPrice.setText(CommonMethodHolder.convertIntToString(totalGoesGreateWithPrice));
                    updateButton();
                    if (productGoesGreateWith.getPortion() == 0) {
                        mTxtViewGoesGreateWithTotalPrice.setVisibility(View.INVISIBLE);
                    }
                }
                break;
            case R.id.imageButtonAddsOnPlus:
                productGoesGreateWith.setPortion(productGoesGreateWith.getPortion() + 1);
                mTxtViewGoesGreateWithPortion.setText((productGoesGreateWith.getPortion() + ""));
                if (productGoesGreateWith.getPortion() > 0) {
                    totalGoesGreateWithPrice = totalGoesGreateWithPrice + CommonMethodHolder.convertStringToInt(productGoesGreateWith.getFood_price()) ;
                    mTxtViewGoesGreateWithTotalPrice.setText(CommonMethodHolder.convertIntToString(totalGoesGreateWithPrice));
                    mTxtViewGoesGreateWithTotalPrice.setVisibility(View.VISIBLE);
                    updateButton();
                }
                break;
            case R.id.buttonReturnMenu:
                onButtonClickListener.onHideSettingProductFragment();
                break;
            case R.id.buttonAddToCart:
                mProductAddsOnForAddingToCartList.add(productSetting);
                if(productGoesGreateWith.getPortion()>0){
                    mProductAddsOnForAddingToCartList.add(productGoesGreateWith);
                }
                for(int i = 0; i < mArrListAddsOn.size(); i++){
                    if(mArrListAddsOn.get(i).getPortion() > 0){
                        mProductAddsOnForAddingToCartList.add(mArrListAddsOn.get(i));
                    }
                }
                onButtonClickListener.onAddToCart(mProductAddsOnForAddingToCartList);
                break;
            case R.id.buttonReturnCart:
                onButtonClickListener.onReturnCart();
                break;
            case R.id.buttonUpdateCart:
                onButtonClickListener.onUpdateCart(productSetting);
                break;
        }
    }

    private void updateButton(){
        grandTotal = totalProductPrice + totalAddsOnPrice + totalGoesGreateWithPrice;
        if(fromCart){
            mBtnUpdateCart.setText((getResources().getString(R.string.updateCart) + "\n" +  CommonMethodHolder.convertIntToString(grandTotal)));
        }else{
            mBtnAddToCart.setText((getResources().getString(R.string.addToCart) + "\n" + CommonMethodHolder.convertIntToString(grandTotal)));
        }
    }

    private void setUpViewClick() {
        // Product Setting
        mImgButtonProductMinus.setOnClickListener(this);
        mImgButtonProductPlus.setOnClickListener(this);
        mBtnReturnMenu.setOnClickListener(this);
        mBtnAddToCart.setOnClickListener(this);
        mBtnUpdateCart.setOnClickListener(this);
        mBtnReturnCart.setOnClickListener(this);

        // Goes Greate With
        mImgButtonGoesGreateWithPlus.setOnClickListener(this);
        mImgButtonGoesGreateWithMinus.setOnClickListener(this);
    }

    private void settingLayout() {
        Log.d("EEE", "At Setting Layout In Setting Product Fragment");
        productSetting = Hawk.get("productSetting");
        mAdapterSlider = new AdapterForSlider(getActivity(), productSetting.getUrls_banner());
        mSliderView.setSliderAdapter(mAdapterSlider);
        mTxtViewProductPortion.setText((productSetting.getPortion() + ""));
        mTxtViewProductName.setText(productSetting.getFood_name());
        mTxtViewProductPrice.setText(productSetting.getFood_price());
        totalProductPrice = CommonMethodHolder.convertStringToInt(productSetting.getFood_price())*productSetting.getPortion();
        mTxtViewProductTotalPrice.setText(CommonMethodHolder.convertIntToString(totalProductPrice));

        // Set up Adds On Product
        mArrListAddsOn = new ArrayList<>();
        FriesAndPepsiAddsOnBanner = new ArrayList<>();
        FriesAndPepsiAddsOnBanner.add("https://kfcvietnam.com.vn/uploads/product/e9d283cd9e2bcdb3ad4c39ceb9a4e132.png");
        mArrListAddsOn.add(new Product(FriesAndPepsiAddsOnBanner, "1 Khoai Tây Chiên (Vừa) & 1 Pepsi Lon", "25.000đ","",  0));
        CheeseAddsOnBanner = new ArrayList<>();
        CheeseAddsOnBanner.add("https://kfcvietnam.com.vn/uploads/product/ebc6cedf8ce80d2385be172b893ddf04.jpg");

        setDefaultPortion();
        changeLayoutBaseOnCaller();


        // Recycler View for line of portion
        mLLManagerSeperateProduct = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        seperateProductAdapter = new SeperateProductAdapter(mArrListName, getActivity());
        mRclViewProductInfo.setLayoutManager(mLLManagerSeperateProduct);
        mRclViewProductInfo.setAdapter(seperateProductAdapter);
    }

    private void setDefaultPortion() {
        // Set default for portions
        mArrListName = new ArrayList<>();
        String[] seperateLineResult = CommonMethodHolder.seperateAsterisk(productSetting.getFood_descript());

        seperateLineResult = CommonMethodHolder.deleteNextLine(seperateLineResult);
        for (int i = 1; i < seperateLineResult.length; i++) {
            String[] defaultPortions = CommonMethodHolder.seperateSlash(seperateLineResult[i]);
            mArrListName.add(new ProductSeperated(defaultPortions[0], defaultPortions));
            // Add Cheese if this combo has burger and setting call from main
            if(defaultPortions[0].length() >=9){
                if (i == 2 && defaultPortions[0].substring(2, 9).trim().equals("Burger") && !fromCart) {
                    mArrListAddsOn.add(new Product(CheeseAddsOnBanner, "1 Phô Mai", "4.000đ", "", 0));
                    Log.d("DDD", "At add cheese");
                }
            }
        }
    }

    private void setUpAddsOnProduct() {

        // Recycler View for adds on menu
        mLLManagerAddsOn = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        addsOnAdapter = new AddsOnAdapter(mArrListAddsOn, getActivity());
        mRclViewAddsOn.setLayoutManager(mLLManagerAddsOn);
        mRclViewAddsOn.setAdapter(addsOnAdapter);

        addsOnAdapter.setOnAddsOnClickListener(new AddsOnAdapter.OnAddsOnClickListener() {
            @Override
            public void onMinusClick(Product productALaCarte) {
                totalAddsOnPrice = totalAddsOnPrice - CommonMethodHolder.convertStringToInt(productALaCarte.getFood_price());
                updateButton();
                updatePortion(productALaCarte);
            }

            @Override
            public void onPlusClick(Product productALaCarte) {
                totalAddsOnPrice = totalAddsOnPrice + CommonMethodHolder.convertStringToInt(productALaCarte.getFood_price());
                updatePortion(productALaCarte);
                updateButton();
            }
        });
    }

    private void changeLayoutBaseOnCaller() {
        if(fromCart){
            mBtnReturnCart.setText(getResources().getString(R.string.returnCart));
            mBtnUpdateCart.setText((getResources().getString(R.string.updateCart) + "\n" +  mTxtViewProductTotalPrice.getText()));
            mLLFromCart.setVisibility(View.VISIBLE);
            mLLFromMain.setVisibility(View.GONE);
            mLLAddsOnAndGoesGreateWith.setVisibility(View.GONE);
        }else{
            mBtnAddToCart.setText((getResources().getString(R.string.addToCart) + "\n" + productSetting.getFood_price()));
            mBtnReturnMenu.setText(getResources().getString(R.string.returnMenu));
            mLLFromMain.setVisibility(View.VISIBLE);
            mLLFromCart.setVisibility(View.GONE);
            mLLAddsOnAndGoesGreateWith.setVisibility(View.VISIBLE);

            setUpAddsOnProduct();
            setUpGoesGreateWithProduct();
        }
    }

    private void setUpGoesGreateWithProduct() {
        // Goes Greate With Product
        ProductGoesGreateWithBanner = new ArrayList<>();
        ProductGoesGreateWithBanner.add("https://kfcvietnam.com.vn/uploads/product/be95ff8508fa5aacb78baba4a6b644c1.jpg");
        productGoesGreateWith = new Product(ProductGoesGreateWithBanner, "Bánh Trứng (4 cái)", "50.000đ","", 0);
    }

    private void initView(View view) {
        // Product Setting
        mTxtViewProductName = view.findViewById(R.id.textViewProductName);
        mTxtViewProductPrice = view.findViewById(R.id.textViewProductPrice);
        mTxtViewProductPortion = view.findViewById(R.id.textViewProductPortion);
        mTxtViewProductTotalPrice = view.findViewById(R.id.textViewProductTotalPrice);
        mImgButtonProductMinus = view.findViewById(R.id.imageButtonProductMinus);
        mImgButtonProductPlus = view.findViewById(R.id.imageButtonProductPlus);
        mBtnReturnMenu        = view.findViewById(R.id.buttonReturnMenu);
        mBtnAddToCart         = view.findViewById(R.id.buttonAddToCart);
        mBtnReturnCart        = view.findViewById(R.id.buttonReturnCart);
        mBtnUpdateCart        = view.findViewById(R.id.buttonUpdateCart);
        mSliderView = view.findViewById(R.id.imageSlider);
        mRclViewProductInfo = view.findViewById(R.id.recyclerViewProductInfo);
        mLLFromCart = view.findViewById(R.id.linearLayoutFromCart);
        mLLFromMain = view.findViewById(R.id.linearLayoutFromMain);

        //Layout holder Goes Greate With and Adds On
        mLLAddsOnAndGoesGreateWith = view.findViewById(R.id.llAddsOnAndGoesGreate);

        // Goes Greate With
        mTxtViewGoesGreateWithName = view.findViewById(R.id.textViewAddsOnName);
        mTxtViewGoesGreateWithPrice = view.findViewById(R.id.textViewAddsOnPrice);
        mTxtViewGoesGreateWithPortion = view.findViewById(R.id.textViewAddsOnPortion);
        mTxtViewGoesGreateWithTotalPrice = view.findViewById(R.id.textViewAddsOnTotalPrice);
        mImgButtonGoesGreateWithMinus = view.findViewById(R.id.imageButtonAddsOnMinus);
        mImgButtonGoesGreateWithPlus = view.findViewById(R.id.imageButtonAddsOnPlus);

        // Adds On
        mRclViewAddsOn = view.findViewById(R.id.recyclerViewAddOns);

        mProductAddsOnForAddingToCartList = new ArrayList<>();
    }

    public void updatePortion(Product productAlaCarte){
        for(int i = 0 ; i< mArrListAddsOn.size() ; i++){
            if(mArrListAddsOn.get(i).getFood_name().equals(productAlaCarte.getFood_name())){
                mArrListAddsOn.get(i).setPortion(productAlaCarte.getPortion());
                break;
            }
        }
    }
    
    public interface OnButtonClickListener {
        void onHideSettingProductFragment();
        void onAddToCart(ArrayList<Product> addToCartList);
        void onReturnCart();
        void onUpdateCart(Product product);
    }


}