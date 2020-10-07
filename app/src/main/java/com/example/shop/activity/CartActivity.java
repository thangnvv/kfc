package com.example.shop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.adapter.SingleProductAdapter;
import com.example.shop.interfaces.OnDeleteItemClickListener;
import com.example.shop.ultil.Product;
import com.example.shop.ultil.Util;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements OnDeleteItemClickListener, View.OnClickListener {
    TextView mTxtViewProductCount, mTxtViewCartTotal;
    int productCount;
    String cartTotal;
    ArrayList<Product> mProductArrList;
    SingleProductAdapter mSingleProductAdapter;
    RecyclerView mRecyclerViewProductInCart;
    LinearLayout mLLCartHasItem, mLLCartEmpty;
    Button mBtnOrderNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initView();
        setUpViewClick();

        productCount = Hawk.get("productCount");
        cartTotal = Hawk.get("cartTotal");
        mProductArrList = Hawk.get("productAddedToCart");
        if(mProductArrList.size() == 0){
            mLLCartHasItem.setVisibility(View.GONE);
        }else{
            mLLCartEmpty.setVisibility(View.GONE);
            setUpView();
        }



    }

    private void setUpViewClick() {
        mBtnOrderNow.setOnClickListener(this);
    }

    private void setUpView() {
        mProductArrList = removeRepeatProduct(mProductArrList);
        for (int i = 0 ; i < mProductArrList.size(); i++){
            mProductArrList.get(i).setFoodDescrip(Util.setDefaultCourse(mProductArrList.get(i).getFoodDescrip()));
        }
        mSingleProductAdapter = new SingleProductAdapter(mProductArrList, this);
        mSingleProductAdapter.setOnDeleteItemClickListener(this);
        mRecyclerViewProductInCart.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewProductInCart.setAdapter(mSingleProductAdapter);

        mTxtViewProductCount.setText(productCount + " món");
        mTxtViewCartTotal.setText(cartTotal);
    }

    private void initView() {
        mTxtViewCartTotal = findViewById(R.id.textViewCartTotal);
        mTxtViewProductCount = findViewById(R.id.textViewProductCount);
        mRecyclerViewProductInCart = findViewById(R.id.recyclerViewProuctInCart);
        mLLCartEmpty    = findViewById(R.id.linearLayoutCartEmpty);
        mLLCartHasItem = findViewById(R.id.linearLayoutHasItem);
        mBtnOrderNow = findViewById(R.id.buttonOrderNow);
    }

    private ArrayList<Product> removeRepeatProduct(ArrayList<Product> arrList){
        int portion;
        for(int i = 0 ; i < arrList.size() - 1; i++){
            portion = 1;
            arrList.get(i).setPortion(1);
            for(int j = i + 1; j< arrList.size() ; j++){
                if(arrList.get(i).getFoodName().equals(arrList.get(j).getFoodName())){
                    portion++;
                    Log.d("DDD", "Remove: " + arrList.get(j).getFoodName());
                    arrList.remove(j);
                    j--;
                }
            }
            arrList.get(i).setPortion(portion);
        }
        return arrList;
    }

    @Override
    public void onDeleteItem(int position, int portion, String totalPrice) {
        mProductArrList.remove(position);
        if(mProductArrList.size() == 0){
            mLLCartHasItem.setVisibility(View.GONE);
            mLLCartEmpty.setVisibility(View.VISIBLE);
        }
            mSingleProductAdapter.notifyDataSetChanged();
            productCount = productCount - portion;
            int recentTotal = Util.convertStringToInt(mTxtViewCartTotal.getText().toString());
            int deletePrice = Util.convertStringToInt(totalPrice);
            mTxtViewCartTotal.setText(Util.convertIntToString(recentTotal - deletePrice));
            mTxtViewProductCount.setText(productCount + " món");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Hawk.put("productAddedToCart" , mProductArrList);
        Hawk.put("productCount", productCount);
        Hawk.put("cartTotal", mTxtViewCartTotal.getText());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonOrderNow:
                finish();
                break;
        }
    }
}