package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.ultil.DownloadImageTask;
import com.example.shop.ultil.Product;
import com.example.shop.ultil.CommonMethodHolder;

import java.util.ArrayList;

public class AddsOnAdapter extends RecyclerView.Adapter<AddsOnAdapter.ViewHolder> {

    ArrayList<Product> mArrListName;
    Context context;
    OnAddsOnClickListener onAddsOnClickListener;

    public AddsOnAdapter(ArrayList<Product> mArrListName, Context context) {
        this.mArrListName = mArrListName;
        this.context = context;
    }

    public void setOnAddsOnClickListener(OnAddsOnClickListener onAddsOnClickListener){
        this.onAddsOnClickListener = onAddsOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_goes_greate_with, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Product productALaCarte = mArrListName.get(position);

        holder.mTxtViewAddsOnName.setText(productALaCarte.getFoodName());
        holder.mTxtViewAddsOnPrice.setText(productALaCarte.getFoodPrice());
        holder.mTxtViewAddsOnPortion.setText("0");

        new DownloadImageTask(holder.mImgViewBanner).execute(productALaCarte.getUrlImageBanner().get(0).getImageUrl());

        holder.mImgButtonAddsOnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productALaCarte.getPortion() > 0) {
                    productALaCarte.setPortion(productALaCarte.getPortion() - 1);
                    holder.mTxtViewAddsOnPortion.setText((productALaCarte.getPortion() + ""));
                    int totalPrice = CommonMethodHolder.convertStringToInt(productALaCarte.getFoodPrice()) * productALaCarte.getPortion();
                    holder.mTxtViewAddsOnTotalPrice.setText(CommonMethodHolder.convertIntToString(totalPrice));
                    if (productALaCarte.getPortion() == 0) {
                        holder.mTxtViewAddsOnTotalPrice.setVisibility(View.INVISIBLE);
                    }
                    onAddsOnClickListener.onMinusClick(productALaCarte);
                }
            }
        });

        holder.mImgButtonAddsOnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productALaCarte.getPortion() == 0) {
                    holder.mTxtViewAddsOnTotalPrice.setVisibility(View.VISIBLE);
                }

                productALaCarte.setPortion(productALaCarte.getPortion() + 1);
                holder.mTxtViewAddsOnPortion.setText((productALaCarte.getPortion() + ""));
                int totalPrice = CommonMethodHolder.convertStringToInt(productALaCarte.getFoodPrice()) * productALaCarte.getPortion();
                holder.mTxtViewAddsOnTotalPrice.setText(CommonMethodHolder.convertIntToString(totalPrice));
                onAddsOnClickListener.onPlusClick(productALaCarte);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrListName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtViewAddsOnName, mTxtViewAddsOnPrice, mTxtViewAddsOnPortion, mTxtViewAddsOnTotalPrice;
        ImageButton mImgButtonAddsOnPlus, mImgButtonAddsOnMinus;
        ImageView mImgViewBanner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTxtViewAddsOnPortion = itemView.findViewById(R.id.textViewAddsOnPortion);
            mTxtViewAddsOnPrice = itemView.findViewById(R.id.textViewAddsOnPrice);
            mTxtViewAddsOnName = itemView.findViewById(R.id.textViewAddsOnName);
            mTxtViewAddsOnTotalPrice = itemView.findViewById(R.id.textViewAddsOnTotalPrice);
            mImgButtonAddsOnPlus = itemView.findViewById(R.id.imageButtonAddsOnPlus);
            mImgButtonAddsOnMinus = itemView.findViewById(R.id.imageButtonAddsOnMinus);
            mImgViewBanner = itemView.findViewById(R.id.imageViewBanner);
        }
    }

    public interface OnAddsOnClickListener{
         void onMinusClick(Product productALaCarte);
         void onPlusClick(Product productALaCarte);
    }


}
