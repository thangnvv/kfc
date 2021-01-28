package com.example.shop.adapter;

import android.content.Context;
import android.util.Log;
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
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.utils.DownloadImageTask;
import com.example.shop.objects.Product;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class ProductALaCarteAdapter extends RecyclerView.Adapter<ProductALaCarteAdapter.ViewHolder>{

    private ArrayList<Product> mProductList;
    private Context mContext;
    OnProductClickListener mOnProductClickListener;

    public void setOnProductClickListener(OnProductClickListener onProductClickListener){
        this.mOnProductClickListener = onProductClickListener;
    }

    public ProductALaCarteAdapter(ArrayList<Product> mProductList, Context mContext) {
        this.mProductList = mProductList;
        this.mContext = mContext;
    }

    public ArrayList<Product> getmProductList() {
        return mProductList;
    }

    public void setmProductList(ArrayList<Product> mProductList) {
        this.mProductList = mProductList;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_product_alacarte, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Product productALaCarte = mProductList.get(position);

        SliderAdapter adapter = new SliderAdapter(getmContext(), productALaCarte.getUrls_banner());
        if(productALaCarte.getUrls_banner().size() == 1){
            holder.mSliderViewBanner.setVisibility(View.GONE);
            holder.imgViewProductBanner.setVisibility(View.VISIBLE);
            new DownloadImageTask(holder.imgViewProductBanner).execute(productALaCarte.getUrls_banner().get(0));
        }else{
            holder.imgViewProductBanner.setVisibility(View.GONE);
            holder.mSliderViewBanner.setVisibility(View.VISIBLE);
        }
        holder.mSliderViewBanner.setSliderAdapter(adapter);

        holder.mTextViewFoodName.setText(productALaCarte.getFood_name());
        holder.mTextViewFoodPrice.setText(productALaCarte.getFood_price());
        holder.mTextViewPortion.setText(productALaCarte.getPortion() + "");

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
                Log.d("EEE", "At Product ALaCarte Adapter");
                mOnProductClickListener.onOrderProduct(mProductList.get(position));
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
        SliderView mSliderViewBanner;
        ImageView imgViewProductBanner;
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
            mSliderViewBanner = itemView.findViewById(R.id.sliderViewProduct);
            imgViewProductBanner     = itemView.findViewById(R.id.imageViewProductBanner);
        }
    }
}
