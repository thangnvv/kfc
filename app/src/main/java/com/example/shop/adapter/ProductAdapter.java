package com.example.shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.ultil.Product;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private ArrayList<Product> mProductList;
    private Context context;
    private boolean expandTextStatus = false;

    public ProductAdapter(ArrayList<Product> mProductList, Context context) {
        this.mProductList = mProductList;
        this.context = context;
    }

    public ArrayList<Product> getmProductList() {
        return mProductList;
    }

    public void setmProductList(ArrayList<Product> mProductList) {
        this.mProductList = mProductList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Product product = mProductList.get(i);

        view                                    = LayoutInflater.from(context).inflate(R.layout.layout_product, null);

        SliderView sliderView                   = view.findViewById(R.id.sliderViewProduct);
        final TextView txtViewProductName       = view.findViewById(R.id.textViewProductName);
        TextView txtViewProductPrice            = view.findViewById(R.id.textViewProductPrice);
        final TextView txtViewProductDescript   = view.findViewById(R.id.textViewDiscrip);
        Button btnSetting                       = view.findViewById(R.id.buttonSettingProduct);
        Button btnOrder                         = view.findViewById(R.id.buttonOrderProduct);
        final ImageButton btnExpandText         = view.findViewById(R.id.imageButtonExpandText);

        AdapterForSlider adapter = new AdapterForSlider(getContext(), product.getUrlImageBanner() );

        sliderView.setSliderAdapter(adapter);



        txtViewProductName.setText(product.getFoodName());
        txtViewProductPrice.setText(product.getFoodPrice());
        txtViewProductDescript.setText(product.getFoodDescrip());

        btnExpandText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expandTextStatus){
                    txtViewProductDescript.setMaxLines(3);
                    btnExpandText.setImageResource(R.drawable.arrow_down);
                    expandTextStatus = false;
                }else {
                    txtViewProductDescript.setMaxLines(Integer.MAX_VALUE);
                    btnExpandText.setImageResource(R.drawable.arrow_up);
                    expandTextStatus = true;
                }
            }
        });

        return view;
    }
}
