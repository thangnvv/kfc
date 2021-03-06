package com.example.shop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.adapter.SingleProductAdapter;
import com.example.shop.interfaces.OnDeleteItemClickListener;
import com.example.shop.objects.Cart;
import com.example.shop.objects.Product;
import com.example.shop.utils.CommonMethodHolder;
import com.example.shop.objects.ProductALaCarte;
import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements OnDeleteItemClickListener, View.OnClickListener {
    private int cartCount;
    private String cartTotal;
    private Cart cart;
    private ArrayList<Product> mProductAddedToCartArrList, mProductShowDefaultsArrList;
    private SingleProductAdapter mSingleProductAdapter;
    private RecyclerView mRecyclerViewProductInCart;
    private LinearLayout mLLCartHasItem, mLLCartEmpty, mLineCartCountTop, mLineCartCountBottom, mLineCartCountGrandTotal;
    private Button mBtnOrderNow, mBtnUseVoucher, mBtnKeepShopping, mBtnPay, mBtnGoToPay;
    private ImageButton mImgButtonQuiteCart;
    private EditText mEdtTextOrderNote;
    private TextInputEditText mTxtInputEdtText;
    private boolean editClick = false;

    //For include layout
    private TextView mTxtViewProductCount, mTxtViewCartTotal, mTxtViewLineInfoCartBottom, mTxtViewLinePriceCartBottom,
            mTxtViewLineInfoCartGrandTotal, mTxtViewLinePriceCartGrandTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        createCart();
        setUpViewClick();
    }

    private void createCart() {
        cart = Hawk.get("cart");
        cartCount = cart.getCartCount();
        cartTotal = cart.getCartTotal();
        mProductAddedToCartArrList = cart.getArrListProductInCart();
        // This array list for holding food description. If not the food descriptions will be change in the process
        mProductShowDefaultsArrList = new ArrayList<>();
        mProductShowDefaultsArrList.addAll(cart.getArrListProductInCart());
        if (mProductAddedToCartArrList.size() == 0) {
            mLLCartHasItem.setVisibility(View.GONE);
            mLLCartEmpty.setVisibility(View.VISIBLE);
        } else {
            mLLCartEmpty.setVisibility(View.GONE);
            mLLCartHasItem.setVisibility(View.VISIBLE);
            setUpView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Restore the food descriptions from array list holder
        for (int i = 0; i < mProductAddedToCartArrList.size(); i++) {
            mProductAddedToCartArrList.get(i).setFood_descript(mProductShowDefaultsArrList.get(i).getFood_descript());
        }
        CommonMethodHolder.saveCart(mProductAddedToCartArrList, cartCount, cartTotal, editClick, cart);
    }

    private void setUpView() {
        removeRepeatProduct();
        mSingleProductAdapter = new SingleProductAdapter(mProductAddedToCartArrList, this);

        mSingleProductAdapter.setOnDeleteItemClickListener(this);
        mRecyclerViewProductInCart.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewProductInCart.setAdapter(mSingleProductAdapter);

        mTxtViewProductCount.setText((cartCount + " món"));
        mTxtViewCartTotal.setText(cartTotal);

        mTxtViewLineInfoCartBottom.setText((cartCount + " món"));
        mTxtViewLinePriceCartBottom.setText(cartTotal);
        mTxtViewLinePriceCartGrandTotal.setText(cartTotal);

    }

    private void setUpViewClick() {
        mBtnOrderNow.setOnClickListener(this);
        mBtnKeepShopping.setOnClickListener(this);
        mBtnPay.setOnClickListener(this);
        mBtnGoToPay.setOnClickListener(this);
        mImgButtonQuiteCart.setOnClickListener(this);


        // Only set up On Click for Adapter when cart is not empty or it would throw null pointer exeption
        if (cartCount > 0) {
            mSingleProductAdapter.setOnViewClickListener(new SingleProductAdapter.OnViewClickListener() {
                @Override
                public void onMinusPortion(Product product) {
                    cartCount = cartCount - 1;
                    cartTotal = CommonMethodHolder.convertIntToString(CommonMethodHolder.convertStringToInt(cartTotal) - CommonMethodHolder.convertStringToInt(product.getFood_price()));
                    mTxtViewCartTotal.setText(cartTotal);
                    mTxtViewProductCount.setText(cartCount + " món");

                    mTxtViewLineInfoCartBottom.setText(cartCount + " món");
                    mTxtViewLinePriceCartBottom.setText(cartTotal);

                    mTxtViewLinePriceCartGrandTotal.setText(cartTotal);
                }

                @Override
                public void onPlusPortion(Product product) {
                    cartCount = cartCount + 1;
                    cartTotal = CommonMethodHolder.convertIntToString(CommonMethodHolder.convertStringToInt(cartTotal) + CommonMethodHolder.convertStringToInt(product.getFood_price()));
                    mTxtViewCartTotal.setText(cartTotal);
                    mTxtViewProductCount.setText(cartCount + " món");

                    mTxtViewLineInfoCartBottom.setText(cartCount + " món");
                    mTxtViewLinePriceCartBottom.setText(cartTotal);

                    mTxtViewLinePriceCartGrandTotal.setText(cartTotal);

                }

                @Override
                public void onEditProduct(Product product) {
                    Hawk.put("productSetting", product);
                    editClick = true;
                    Intent intentGoToMain = new Intent(CartActivity.this, MainActivity.class);
                    startActivity(intentGoToMain);
                }
            });
        }

    }

    private void initView() {
        Hawk.init(getApplicationContext()).build();
        mRecyclerViewProductInCart = findViewById(R.id.recyclerViewProuctInCart);
        mLLCartEmpty = findViewById(R.id.linearLayoutCartEmpty);
        mLLCartHasItem = findViewById(R.id.linearLayoutHasItem);
        mBtnOrderNow = findViewById(R.id.buttonOrderNow);
        mBtnUseVoucher = findViewById(R.id.buttonUseVoucher);
        mBtnKeepShopping = findViewById(R.id.buttonKeepShopping);
        mBtnPay = findViewById(R.id.buttonPay);
        mBtnGoToPay = findViewById(R.id.buttonGotoPay);
        mImgButtonQuiteCart = findViewById(R.id.imageButtonQuitCart);
        mTxtInputEdtText = findViewById(R.id.textInputEdtTextVoucher);
        mEdtTextOrderNote = findViewById(R.id.editTextOrderNote);

        mLineCartCountTop = findViewById(R.id.lineCartCountTop);
        mTxtViewCartTotal = mLineCartCountTop.findViewById(R.id.textViewLinePrice);
        mTxtViewProductCount = mLineCartCountTop.findViewById(R.id.textViewLineInfo);

        mLineCartCountBottom = findViewById(R.id.lineCartCountBottom);
        mTxtViewLineInfoCartBottom = mLineCartCountBottom.findViewById(R.id.textViewLineInfo);
        mTxtViewLinePriceCartBottom = mLineCartCountBottom.findViewById(R.id.textViewLinePrice);

        mLineCartCountGrandTotal = findViewById(R.id.lineCartCountGrandTotal);
        mTxtViewLineInfoCartGrandTotal = mLineCartCountGrandTotal.findViewById(R.id.textViewLineInfo);
        mTxtViewLinePriceCartGrandTotal = mLineCartCountGrandTotal.findViewById(R.id.textViewLinePrice);
        mTxtViewLineInfoCartGrandTotal.setAllCaps(true);
        mTxtViewLineInfoCartGrandTotal.setTextSize(getResources().getDimension(R.dimen._7ssp));
        mTxtViewLineInfoCartGrandTotal.setText("TỔNG ĐƠN HÀNG");
        mTxtViewLinePriceCartGrandTotal.setTextSize(getResources().getDimension(R.dimen._7ssp));
        mTxtViewLinePriceCartGrandTotal.setTextColor(getResources().getColor(R.color.red));
    }

    private void removeRepeatProduct() {
        // remove and add portion if 2 product have the same name and price and alacartes
        for (int i = 0; i < mProductAddedToCartArrList.size() - 1; i++) {
            Product productTemp1 = mProductAddedToCartArrList.get(i);
            // If Product Alacarte is null meaning this is adds on or goes greate with product
            if (productTemp1.getAlacarte() != null) {
                for (int j = i + 1; j < mProductAddedToCartArrList.size(); j++) {
                    boolean isTheSame = true;
                    Product productTemp2 = mProductAddedToCartArrList.get(j);
                    // Check if the same name and price
                    if (productTemp1.getFood_name().equals(productTemp2.getFood_name()) && productTemp1.getFood_price().equals(productTemp2.getFood_price())) {
                        for (int k = 0; k < productTemp1.getAlacarte().size(); k++) {
                            // check if the same alacarte
                            ProductALaCarte aLaCarte1 = productTemp1.getAlacarte().get(k);
                            if (!aLaCarte1.isUpgradable()) {
                                ProductALaCarte aLaCarte2 = productTemp2.getAlacarte().get(k);
                                if (!aLaCarte1.getChosen_alacarte().equals(aLaCarte2.getChosen_alacarte())) {
                                    isTheSame = false;
                                    break;
                                }
                            }
                        }
                        // Remove the product with the same price, name, alacartes
                        if (isTheSame) {
                            mProductAddedToCartArrList.get(i).setPortion(mProductAddedToCartArrList.get(i).getPortion() + 1);
                            mProductAddedToCartArrList.remove(j);
                            j--;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onItemDeleted(int position, int portion, String totalPrice) {
        mProductAddedToCartArrList.remove(position);
        mProductShowDefaultsArrList.remove(position);
        if (mProductAddedToCartArrList.size() == 0) {
            mLLCartHasItem.setVisibility(View.GONE);
            mLLCartEmpty.setVisibility(View.VISIBLE);
        }
        mSingleProductAdapter.notifyDataSetChanged();
        cartCount = cartCount - portion;
        int recentTotal = CommonMethodHolder.convertStringToInt(mTxtViewCartTotal.getText().toString());
        int deletePrice = CommonMethodHolder.convertStringToInt(totalPrice);
        cartTotal = CommonMethodHolder.convertIntToString(recentTotal - deletePrice);
        mTxtViewCartTotal.setText(cartTotal);
        mTxtViewProductCount.setText(cartCount + " món");
        CommonMethodHolder.saveCart(mProductAddedToCartArrList, cartCount, cartTotal, false, cart);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonQuitCart:
            case R.id.buttonKeepShopping:
                Hawk.put("isKeepShopping", true);
                finish();
                break;
            case R.id.buttonOrderNow:
                mProductAddedToCartArrList.clear();
                cartTotal = "";
                cartCount = 0;
                finish();
                break;
            case R.id.buttonGotoPay:
            case R.id.buttonPay:
                Intent intentCheckOut = new Intent(CartActivity.this, CheckOutActivity.class);
                startActivity(intentCheckOut);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initView();
        createCart();
        setUpViewClick();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Hawk.put("isKeepShopping", false);
        finish();
    }
}