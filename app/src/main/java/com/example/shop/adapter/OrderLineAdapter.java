package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.utils.CommonMethodHolder;
import com.example.shop.objects.Product;

import java.util.ArrayList;

public class OrderLineAdapter extends RecyclerView.Adapter<OrderLineAdapter.ViewHolder>{

    ArrayList<Product> mProductArrList;
    Context context;

    public OrderLineAdapter(ArrayList<Product> mProductArrList, Context context) {
        this.mProductArrList = mProductArrList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product_portion_added_to_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = (Product) mProductArrList.get(position);
        int foodTotalPrice = CommonMethodHolder.convertStringToInt(product.getFood_price())*product.getPortion();
        holder.mTxtViewProductInfo.setText(product.getPortion() + "x " + product.getFood_name());
        holder.mTxtViewProductPrice.setText(CommonMethodHolder.convertIntToString(foodTotalPrice));
    }

    @Override
    public int getItemCount() {
        return mProductArrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTxtViewProductInfo, mTxtViewProductPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtViewProductInfo = itemView.findViewById(R.id.textViewProductInfo);
            mTxtViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
        }
    }
}
