package com.example.shop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.adapter.SingleProductAdapter;
import com.example.shop.interfaces.OnDeleteItemClickListener;
import com.example.shop.ultil.Cart;
import com.example.shop.ultil.Product;
import com.example.shop.ultil.CommonMethodHolder;
import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements OnDeleteItemClickListener, View.OnClickListener {
    int cartCount;
    String cartTotal;
    Cart cart;
    ArrayList<Product> mProductAddedToCartArrList, mProductShowDefaulsArrList;
    SingleProductAdapter mSingleProductAdapter;
    RecyclerView mRecyclerViewProductInCart;
    LinearLayout mLLCartHasItem, mLLCartEmpty, mLineCartCountTop, mLineCartCountBottom, mLineCartCountGrandTotal;
    Button mBtnOrderNow, mBtnUseVoucher, mBtnKeepShopping, mBtnPay;
    ImageButton mImgButtonQuiteCart;
    EditText mEdtTextOrderNote;
    TextInputEditText mTxtInputEdtText;
    boolean editClick = false;

    //For include layout
    TextView mTxtViewProductCount, mTxtViewCartTotal, mTxtViewLineInfoCartBottom, mTxtViewLinePriceCartBottom,
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
        mProductShowDefaulsArrList = new ArrayList<>();
        mProductShowDefaulsArrList.addAll(cart.getArrListProductInCart());
        if (mProductAddedToCartArrList.size() == 0) {
            mLLCartHasItem.setVisibility(View.GONE);
            mLLCartEmpty.setVisibility(View.VISIBLE);
        } else {
            Log.d("KKK", "In Cart: " + mProductAddedToCartArrList.size());
            mLLCartEmpty.setVisibility(View.GONE);
            mLLCartHasItem.setVisibility(View.VISIBLE);
            setUpView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (int i = 0; i < mProductAddedToCartArrList.size(); i++) {
            mProductAddedToCartArrList.get(i).setFood_descript(mProductShowDefaulsArrList.get(i).getFood_descript());
        }
        CommonMethodHolder.saveCart(mProductAddedToCartArrList, cartCount, cartTotal, editClick, cart);
        Log.d("DDD", "At On Pause Cart");
    }

    private void setUpView() {
        removeRepeatProduct();
        for (int i = 0; i < mProductAddedToCartArrList.size(); i++) {
            mProductAddedToCartArrList.get(i).setFood_descript(CommonMethodHolder.setDefaultCourse(mProductAddedToCartArrList.get(i).getFood_descript()));
        }
        mSingleProductAdapter = new SingleProductAdapter(mProductAddedToCartArrList, this);

        mSingleProductAdapter.setOnDeleteItemClickListener(this);
        mRecyclerViewProductInCart.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewProductInCart.setAdapter(mSingleProductAdapter);

        mTxtViewProductCount.setText(cartCount + " món");
        mTxtViewCartTotal.setText(cartTotal);

        mTxtViewLineInfoCartBottom.setText(cartCount + " món");
        mTxtViewLinePriceCartBottom.setText(cartTotal);
        mTxtViewLinePriceCartGrandTotal.setText(cartTotal);

    }

    private void setUpViewClick() {
        mBtnOrderNow.setOnClickListener(this);
        mBtnKeepShopping.setOnClickListener(this);
        mBtnPay.setOnClickListener(this);
        mImgButtonQuiteCart.setOnClickListener(this);

        // Only set up On Click for Adapter when cart is not empty or it would throw null pointer exeption
        if(cartCount >0){
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
        mRecyclerViewProductInCart = findViewById(R.id.recyclerViewProuctInCart);
        mLLCartEmpty = findViewById(R.id.linearLayoutCartEmpty);
        mLLCartHasItem = findViewById(R.id.linearLayoutHasItem);
        mBtnOrderNow = findViewById(R.id.buttonOrderNow);
        mBtnUseVoucher = findViewById(R.id.buttonUseVoucher);
        mBtnKeepShopping = findViewById(R.id.buttonKeepShopping);
        mBtnPay = findViewById(R.id.buttonPay);
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
        for (int i = 0; i < mProductAddedToCartArrList.size() - 1; i++) {
            for (int j = i + 1; j < mProductAddedToCartArrList.size(); j++) {
                if (mProductAddedToCartArrList.get(i).getFood_name().equals(mProductAddedToCartArrList.get(j).getFood_name())) {
                    mProductAddedToCartArrList.get(i).setPortion(mProductAddedToCartArrList.get(i).getPortion() + 1);
                    mProductAddedToCartArrList.remove(j);
                    j--;
                }
            }
        }
    }

    @Override
    public void onItemDeleted(int position, int portion, String totalPrice) {
        mProductAddedToCartArrList.remove(position);
        mProductShowDefaulsArrList.remove(position);
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