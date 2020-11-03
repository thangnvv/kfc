package com.example.shop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.interfaces.OnDeleteItemClickListener;
import com.example.shop.ultil.Product;
import com.example.shop.ultil.CommonMethodHolder;

import java.util.ArrayList;

public class SingleProductAdapter extends RecyclerView.Adapter<SingleProductAdapter.ViewHolder>{

    ArrayList<Product> mProductArrList;
    String foodPrice;
    Context context;
    OnDeleteItemClickListener onDeleteItemClickListener;
    OnViewClickListener onViewClickListener;

    public SingleProductAdapter(ArrayList<Product> mProductArrList, Context context) {
        this.mProductArrList = mProductArrList;
        this.context = context;
    }

    public void setOnViewClickListener(OnViewClickListener onViewClickListener){
        this.onViewClickListener = onViewClickListener;
    }

    public void setOnDeleteItemClickListener(OnDeleteItemClickListener onDeleteItemClickListener){
        this.onDeleteItemClickListener = onDeleteItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product_in_cart, parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Product product = mProductArrList.get(position);
        holder.mTxtViewProductName.setText(product.getFoodName());
        holder.mTxtViewProductInfo.setText(product.getFoodDescrip());
        holder.mTxtViewPortion.setText(product.getPortion() + "");
        if(product.getPortion() > 1){
            foodPrice = calculateFoodPrice(product.getFoodPrice(), product.getPortion());
            holder.mTxtViewProductPrice.setText(foodPrice);
        }else{
            holder.mTxtViewProductPrice.setText(product.getFoodPrice());
        }
        holder.mImgButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setPortion(product.getPortion() + 1);
                foodPrice = calculateFoodPrice(product.getFoodPrice(), product.getPortion());
                holder.mTxtViewPortion.setText(product.getPortion() + "");
                holder.mTxtViewProductPrice.setText(foodPrice);
                onViewClickListener.onPlusPortion(product);
            }
        });
        holder.mImgButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(product.getPortion() > 1){
                    product.setPortion(product.getPortion() - 1);
                    foodPrice = calculateFoodPrice(product.getFoodPrice(), product.getPortion());
                    holder.mTxtViewPortion.setText(product.getPortion() + "");
                    holder.mTxtViewProductPrice.setText(foodPrice);
                    onViewClickListener.onMinusPortion(product);
                }
            }
        });
        holder.mImgButtonDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteItemClickListener.onItemDeleted(position,
                        mProductArrList.get(position).getPortion(),
                        holder.mTxtViewProductPrice.getText().toString());
            }
        });

        holder.mImgButtonEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewClickListener.onEditProduct(product);
            }
        });
    }

    private String calculateFoodPrice(String foodPrice, int portion) {
        int price = CommonMethodHolder.convertStringToInt(foodPrice);
        price = price*portion;
        return CommonMethodHolder.convertIntToString(price);
    }

    @Override
    public int getItemCount() {
        return mProductArrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTxtViewProductName, mTxtViewProductInfo, mTxtViewProductPrice, mTxtViewPortion;
        ImageButton mImgButtonEditProduct, mImgButtonDeleteProduct, mImgButtonMinus, mImgButtonPlus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTxtViewProductName = itemView.findViewById(R.id.textViewSingleProductName);
            mTxtViewProductInfo = itemView.findViewById(R.id.textViewSingleProductInfo);
            mTxtViewProductPrice = itemView.findViewById(R.id.textViewSingleProductPrice);
            mTxtViewPortion = itemView.findViewById(R.id.textViewSingleProductPortion);
            mImgButtonEditProduct = itemView.findViewById(R.id.imageButtonEditProduct);
            mImgButtonDeleteProduct = itemView.findViewById(R.id.imageButtonDeleteProduct);
            mImgButtonMinus = itemView.findViewById(R.id.imageButtonSingleProductMinus);
            mImgButtonPlus = itemView.findViewById(R.id.imageButtonSingleProductPlus);
        }
    }

    public interface OnViewClickListener {
        void onMinusPortion(Product product);
        void onPlusPortion(Product product);
        void onEditProduct(Product product);
    }
}
