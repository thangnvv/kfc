package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.interfaces.OnALaCarteProductClickListener;
import com.example.shop.ultil.DownloadImageTask;
import com.example.shop.ultil.ProductALaCarte;

import java.util.ArrayList;

public class ProductALaCarteAdapter extends RecyclerView.Adapter<ProductALaCarteAdapter.ViewHolder>{

    private ArrayList<ProductALaCarte> mProductList;
    private Context context;
    OnALaCarteProductClickListener onALaCarteProductClickListener;

    public void setOnALaCarteProductClickListener(OnALaCarteProductClickListener onALaCarteProductClickListener){
        this.onALaCarteProductClickListener = onALaCarteProductClickListener;
    }

    public ProductALaCarteAdapter(ArrayList<ProductALaCarte> mProductList, Context context) {
        this.mProductList = mProductList;
        this.context = context;
    }

    public ArrayList<ProductALaCarte> getmProductList() {
        return mProductList;
    }

    public void setmProductList(ArrayList<ProductALaCarte> mProductList) {
        this.mProductList = mProductList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product_alacarte, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ProductALaCarte productALaCarte = mProductList.get(position);

        holder.mTextViewFoodName.setText(productALaCarte.getFoodName());
        holder.mTextViewFoodPrice.setText(productALaCarte.getFoodPrice());
        holder.mTextViewPortion.setText(productALaCarte.getPortion() + "");
        new DownloadImageTask(holder.mImgViewBanner).execute(productALaCarte.getUrlImageBanner());

        holder.mImgButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productALaCarte.getPortion() <= 1){

                }else{
                    productALaCarte.setPortion(productALaCarte.getPortion() - 1);
                    holder.mTextViewPortion.setText(productALaCarte.getPortion() + "");
                }
            }
        });
        holder.mImgButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productALaCarte.setPortion(productALaCarte.getPortion() + 1);
                holder.mTextViewPortion.setText(productALaCarte.getPortion() + "");
            }
        });
        holder.mBtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onALaCarteProductClickListener.onALaCarteOrderClickListener(mProductList.get(position).getFoodPrice());
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewFoodName, mTextViewFoodPrice, mTextViewPortion;
        ImageView mImgViewBanner;
        Button mBtnOrder;
        ImageButton mImgButtonPlus, mImgButtonMinus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewFoodName = itemView.findViewById(R.id.txtViewProductName);
            mTextViewFoodPrice = itemView.findViewById(R.id.txtViewProductPrice);
            mTextViewPortion = itemView.findViewById(R.id.txtViewPortion);
            mImgButtonMinus = itemView.findViewById(R.id.imgButtonMinus);
            mImgButtonPlus = itemView.findViewById(R.id.imgButtonPlus);
            mBtnOrder = itemView.findViewById(R.id.btnOrderProduct);
            mImgViewBanner = itemView.findViewById(R.id.imgViewBanner);
        }
    }
}
