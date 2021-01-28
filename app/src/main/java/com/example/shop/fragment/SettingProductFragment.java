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
import com.example.shop.adapter.SliderAdapter;
import com.example.shop.adapter.AddsOnAdapter;
import com.example.shop.adapter.SeperateProductAdapter;
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.objects.Product;
import com.example.shop.objects.ProductALaCarte;
import com.example.shop.objects.ProductSeperated;
import com.example.shop.utils.CommonMethodHolder;
import com.example.shop.objects.Upgrade;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class SettingProductFragment extends Fragment implements View.OnClickListener {

    // Product Setting
    private TextView mTxtViewProductName, mTxtViewProductPrice, mTxtViewProductPortion, mTxtViewProductTotalPrice;
    private ImageButton mImgButtonProductMinus, mImgButtonProductPlus;
    private Button mBtnReturnMenu, mBtnAddToCart, mBtnReturnCart, mBtnUpdateCart;
    private LinearLayoutManager mLLManagerSeparateProduct;
    private SeperateProductAdapter seperateProductAdapter;
    private RecyclerView mRclViewProductInfo;
    private SliderAdapter mAdapterSlider;
    private SliderView mSliderView;
    private ArrayList<ProductSeperated> mProductSeparates;
    private Product productSetting;
    private OnButtonClickListener onButtonClickListener;
    private LinearLayout mLLFromMain, mLLFromCart;
    private boolean fromCart;
    private boolean isHasCheese = false;
    private int grandTotal, totalProductPrice, totalAddsOnPrice = 0, totalGoesGrateWithPrice = 0;

    //Layout holder Goes Great With and Adds On
    LinearLayout mLLAddsOnAndGoesGreatWith;

    // Goes Great With
    TextView mTxtViewGoesGreatWithName, mTatViewGoesGrateWithPrice, mTxtViewGoesGreatWithTotalPrice, mTxtViewGoesGreatWithPortion;
    ImageButton mImfButtonGoesGrateWithMinus, mImfButtonGoesGrateWithPlus;
    Product productGoesGrateWith;
    ArrayList<String> ProductGoesGrateWithBanner;

    // Adds On
    LinearLayoutManager mLLManagerAddsOn;
    ArrayList<Product> mArrListAddsOn;
    AddsOnAdapter addsOnAdapter;
    RecyclerView mRclViewAddsOn;
    ArrayList<String> FriesAndPepsiAddsOnBanner, CheeseAddsOnBanner;
    OnProductClickListener onProductClickListener;

    ArrayList<Product> mProductAddsOnForAddingToCartList;

    public SettingProductFragment() {
        // Required empty public constructor
    }

    public SettingProductFragment(boolean fromCart) {
        this.fromCart = fromCart;
    }

    public void setOnProductClickListener(OnProductClickListener onProductClickListener) {
        this.onProductClickListener = onProductClickListener;
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
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
                if (productGoesGrateWith.getPortion() > 0) {
                    productGoesGrateWith.setPortion(productGoesGrateWith.getPortion() - 1);
                    mTxtViewGoesGreatWithPortion.setText((productGoesGrateWith.getPortion() + ""));
                    totalGoesGrateWithPrice = totalGoesGrateWithPrice - CommonMethodHolder.convertStringToInt(productGoesGrateWith.getFood_price());
                    mTxtViewGoesGreatWithTotalPrice.setText(CommonMethodHolder.convertIntToString(totalGoesGrateWithPrice));
                    updateButton();
                    if (productGoesGrateWith.getPortion() == 0) {
                        mTxtViewGoesGreatWithTotalPrice.setVisibility(View.INVISIBLE);
                    }
                }
                break;
            case R.id.imageButtonAddsOnPlus:
                productGoesGrateWith.setPortion(productGoesGrateWith.getPortion() + 1);
                mTxtViewGoesGreatWithPortion.setText((productGoesGrateWith.getPortion() + ""));
                if (productGoesGrateWith.getPortion() > 0) {
                    totalGoesGrateWithPrice = totalGoesGrateWithPrice + CommonMethodHolder.convertStringToInt(productGoesGrateWith.getFood_price());
                    mTxtViewGoesGreatWithTotalPrice.setText(CommonMethodHolder.convertIntToString(totalGoesGrateWithPrice));
                    mTxtViewGoesGreatWithTotalPrice.setVisibility(View.VISIBLE);
                    updateButton();
                }
                break;
            case R.id.buttonReturnMenu:
                onButtonClickListener.onHideSettingProductFragment();
                break;
            case R.id.buttonAddToCart:
                // Convert product to String and then convert String to product
                // so that when the product setting change it's  value the product in cart won't be effected
                Gson gson = new Gson();
                String productJson = gson.toJson(productSetting);
                Product product = gson.fromJson(productJson, Product.class);

                mProductAddsOnForAddingToCartList.add(product);
                if (productGoesGrateWith.getPortion() > 0) {
                    mProductAddsOnForAddingToCartList.add(productGoesGrateWith);
                }
                for (int i = 0; i < mArrListAddsOn.size(); i++) {
                    if (mArrListAddsOn.get(i).getPortion() > 0) {
                        mProductAddsOnForAddingToCartList.add(mArrListAddsOn.get(i));
                    }
                }
                onButtonClickListener.onAddToCart(mProductAddsOnForAddingToCartList);
                mProductAddsOnForAddingToCartList.clear();
                mArrListAddsOn.clear();

                break;

            case R.id.buttonReturnCart:
                onButtonClickListener.onReturnCart();
                break;
            case R.id.buttonUpdateCart:
                Log.d("DDD", "At Button Update Cart: " + productSetting.getAlacarte().get(0).getChosen_alacarte());
                onButtonClickListener.onUpdateCart(productSetting);
                break;
        }
    }

    private void updateButton() {
        grandTotal = totalProductPrice + totalAddsOnPrice + totalGoesGrateWithPrice;
        if (fromCart) {
            mBtnUpdateCart.setText((getResources().getString(R.string.updateCart) + "\n" + CommonMethodHolder.convertIntToString(grandTotal)));
        } else {
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
        mImfButtonGoesGrateWithPlus.setOnClickListener(this);
        mImfButtonGoesGrateWithMinus.setOnClickListener(this);
    }

    private void settingLayout() {
        productSetting = Hawk.get("productSetting");

        // Set up Adds On Product
        mArrListAddsOn = new ArrayList<>();
        FriesAndPepsiAddsOnBanner = new ArrayList<>();
        FriesAndPepsiAddsOnBanner.add("https://kfcvietnam.com.vn/uploads/product/e9d283cd9e2bcdb3ad4c39ceb9a4e132.png");
        mArrListAddsOn.add(new Product(FriesAndPepsiAddsOnBanner, "1 Khoai Tây Chiên (Vừa) & 1 Pepsi Lon", "25.000đ", "", 0));
        CheeseAddsOnBanner = new ArrayList<>();
        CheeseAddsOnBanner.add("https://kfcvietnam.com.vn/uploads/product/ebc6cedf8ce80d2385be172b893ddf04.jpg");

        int priceChange = 0;
        for (int i = 0; i < productSetting.getAlacarte().size(); i++) {
            ProductALaCarte aLaCarte = productSetting.getAlacarte().get(i);
            if (aLaCarte.isUpgradable()) {
                String alacartePriceChange = aLaCarte.getUpgrades().get(aLaCarte.getChosen_upgrade_position()).getPrice_change();
                if (!alacartePriceChange.equals("")) {
                    priceChange = priceChange + CommonMethodHolder.convertStringToInt(aLaCarte.getUpgrades().get(aLaCarte.getChosen_upgrade_position()).getPrice_change());
                }
            } else {
                setDefaultPortion(aLaCarte.getChosen_alacarte());
            }
        }
        int originalPrice = CommonMethodHolder.convertStringToInt(productSetting.getFood_price()) - priceChange;
        mAdapterSlider = new SliderAdapter(getActivity(), productSetting.getUrls_banner());
        mSliderView.setSliderAdapter(mAdapterSlider);
        mTxtViewProductPortion.setText((productSetting.getPortion() + ""));
        mTxtViewProductName.setText(productSetting.getFood_name());
        mTxtViewProductPrice.setText(CommonMethodHolder.convertIntToString(originalPrice));

        totalProductPrice = CommonMethodHolder.convertStringToInt(productSetting.getFood_price()) * productSetting.getPortion();
        mTxtViewProductTotalPrice.setText(CommonMethodHolder.convertIntToString(totalProductPrice));

        changeLayoutBaseOnCaller();

        // Recycler View for line of portion
        mLLManagerSeparateProduct = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        seperateProductAdapter = new SeperateProductAdapter(productSetting.getAlacarte(), mProductSeparates, getActivity());

        mRclViewProductInfo.setLayoutManager(mLLManagerSeparateProduct);
        mRclViewProductInfo.setAdapter(seperateProductAdapter);
        seperateProductAdapter.setOnAlacarteUpgraded(new SeperateProductAdapter.OnAlacarteUpgraded() {
            @Override
            public void onUpgraded(int priceChange) {
                int productPriceUpdated = CommonMethodHolder.convertStringToInt(productSetting.getFood_price()) + priceChange;
                productSetting.setFood_price(CommonMethodHolder.convertIntToString(productPriceUpdated));
                mTxtViewProductTotalPrice.setText(productSetting.getFood_price());
                totalProductPrice = totalProductPrice + priceChange;
                updateButton();
            }

            @Override
            public void onChangeOption(String lastChosen, String newChosen) {
                for (int i = 0; i < productSetting.getAlacarte().size(); i++) {
                    if (!productSetting.getAlacarte().get(i).isUpgradable()) {
                        String alacarteName = productSetting.getAlacarte().get(i).getChosen_alacarte();
                        if (alacarteName.trim().equals(lastChosen.trim())) {
                            productSetting.getAlacarte().get(i).setChosen_alacarte(newChosen);
                        }
                    }
                }
            }

            @Override
            public void onMultipleUpgrade(ArrayList<Upgrade> upgradeList, int position) {
               for(int i = 1; i< upgradeList.size(); i++){
                   Gson gson = new Gson();
                   ProductALaCarte aLaCarte = productSetting.getAlacarte().get(position);
                   String productAlacarteJson = gson.toJson(aLaCarte);
                   ProductALaCarte productALaCarte = gson.fromJson(productAlacarteJson, ProductALaCarte.class);
//                   productALaCarte.getUpgrades().get(productALaCarte.getUpgrades().size() - 1).setPortion(upgradeList.get(upgradeList.size() -1).getPortion());
                   productSetting.getAlacarte().add(productALaCarte);
                   seperateProductAdapter.notifyDataSetChanged();
               }
            }
        });
    }

    private void setDefaultPortion(String chosenAlacarte) {
        // Set default for portions
        String[] separateLineResult = CommonMethodHolder.deleteNextLine(CommonMethodHolder.separateAsterisk(productSetting.getFood_descript()));
        for (int i = 1; i < separateLineResult.length; i++) {
            String[] options = CommonMethodHolder.separateSlash(separateLineResult[i]);
            mProductSeparates.add(new ProductSeperated(chosenAlacarte, options));

            // Add Cheese if this combo has burger and setting call from main
            if (options[0].length() >= 9) {
                if (i == 2 && options[0].substring(2, 9).trim().equals("Burger") && !fromCart) {
                    if(!isHasCheese){
                        mArrListAddsOn.add(new Product(CheeseAddsOnBanner, "1 Phô Mai", "4.000đ", "", 0));
                        isHasCheese = true;
                    }
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
        if (fromCart) {
            mBtnReturnCart.setText(getResources().getString(R.string.returnCart));
            mBtnUpdateCart.setText((getResources().getString(R.string.updateCart) + "\n" + mTxtViewProductTotalPrice.getText()));
            mLLFromCart.setVisibility(View.VISIBLE);
            mLLFromMain.setVisibility(View.GONE);
            mLLAddsOnAndGoesGreatWith.setVisibility(View.GONE);
        } else {
            mBtnAddToCart.setText((getResources().getString(R.string.addToCart) + "\n" + productSetting.getFood_price()));
            mBtnReturnMenu.setText(getResources().getString(R.string.returnMenu));
            mLLFromMain.setVisibility(View.VISIBLE);
            mLLFromCart.setVisibility(View.GONE);
            mLLAddsOnAndGoesGreatWith.setVisibility(View.VISIBLE);

            setUpAddsOnProduct();
            setUpGoesGrateWithProduct();
        }
    }

    private void setUpGoesGrateWithProduct() {
        // Goes Great With Product
        ProductGoesGrateWithBanner = new ArrayList<>();
        ProductGoesGrateWithBanner.add("https://kfcvietnam.com.vn/uploads/product/be95ff8508fa5aacb78baba4a6b644c1.jpg");
        productGoesGrateWith = new Product(ProductGoesGrateWithBanner, "Bánh Trứng (4 cái)", "50.000đ", "", 0);
    }

    private void initView(View view) {
        // Product Setting
        mTxtViewProductName = view.findViewById(R.id.textViewProductName);
        mTxtViewProductPrice = view.findViewById(R.id.textViewProductPrice);
        mTxtViewProductPortion = view.findViewById(R.id.textViewProductPortion);
        mTxtViewProductTotalPrice = view.findViewById(R.id.textViewProductTotalPrice);
        mImgButtonProductMinus = view.findViewById(R.id.imageButtonProductMinus);
        mImgButtonProductPlus = view.findViewById(R.id.imageButtonProductPlus);
        mBtnReturnMenu = view.findViewById(R.id.buttonReturnMenu);
        mBtnAddToCart = view.findViewById(R.id.buttonAddToCart);
        mBtnReturnCart = view.findViewById(R.id.buttonReturnCart);
        mBtnUpdateCart = view.findViewById(R.id.buttonUpdateCart);
        mSliderView = view.findViewById(R.id.imageSlider);
        mRclViewProductInfo = view.findViewById(R.id.recyclerViewProductInfo);
        mLLFromCart = view.findViewById(R.id.linearLayoutFromCart);
        mLLFromMain = view.findViewById(R.id.linearLayoutFromMain);

        //Layout holder Goes Great With and Adds On
        mLLAddsOnAndGoesGreatWith = view.findViewById(R.id.llAddsOnAndGoesGreat);

        // Goes Great With
        mTxtViewGoesGreatWithName = view.findViewById(R.id.textViewAddsOnName);
        mTatViewGoesGrateWithPrice = view.findViewById(R.id.textViewAddsOnPrice);
        mTxtViewGoesGreatWithPortion = view.findViewById(R.id.textViewAddsOnPortion);
        mTxtViewGoesGreatWithTotalPrice = view.findViewById(R.id.textViewAddsOnTotalPrice);
        mImfButtonGoesGrateWithMinus = view.findViewById(R.id.imageButtonAddsOnMinus);
        mImfButtonGoesGrateWithPlus = view.findViewById(R.id.imageButtonAddsOnPlus);

        // Adds On
        mRclViewAddsOn = view.findViewById(R.id.recyclerViewAddOns);

        mProductAddsOnForAddingToCartList = new ArrayList<>();
        mProductSeparates = new ArrayList<>();

    }

    public void updatePortion(Product productAlaCarte) {
        for (int i = 0; i < mArrListAddsOn.size(); i++) {
            if (mArrListAddsOn.get(i).getFood_name().equals(productAlaCarte.getFood_name())) {
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