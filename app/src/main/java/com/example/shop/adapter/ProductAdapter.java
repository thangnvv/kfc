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
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.utils.DownloadImageTask;
import com.example.shop.utils.objects.Product;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Product> mProductList;
    private Context mContext;
    private boolean isExpandedText = false;
    OnProductClickListener mOnProductClickListener;

    // This method set callback for fragment
    public void setOnProductClickListener(OnProductClickListener onProductClickListener){
        this.mOnProductClickListener = onProductClickListener;
    }

    public ProductAdapter(ArrayList<Product> mProductList, Context mContext) {
        this.mProductList = mProductList;
        this.mContext = mContext;
    }

    public Context getmContext() {
        return mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Product product = mProductList.get(position);
        SliderAdapter adapter = new SliderAdapter(getmContext(), product.getUrls_banner() );
        if(product.getUrls_banner().size() == 1){
            holder.sliderView.setVisibility(View.GONE);
            holder.imgViewProductBanner.setVisibility(View.VISIBLE);
            new DownloadImageTask(holder.imgViewProductBanner).execute(product.getUrls_banner().get(0));
        }else{
            holder.imgViewProductBanner.setVisibility(View.GONE);
            holder.sliderView.setVisibility(View.VISIBLE);
        }
        holder.sliderView.setSliderAdapter(adapter);

        holder.txtViewProductName.setText(product.getFood_name());
        holder.txtViewProductPrice.setText(product.getFood_price());
        holder.txtViewProductDescript.setText(product.getFood_descript());

        holder.btnExpandText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isExpandedText){
                    holder.txtViewProductDescript.setMaxLines(3);
                    holder.btnExpandText.setImageResource(R.drawable.arrow_down);
                    isExpandedText = false;
                }else {
                    holder.txtViewProductDescript.setMaxLines(Integer.MAX_VALUE);
                    holder.btnExpandText.setImageResource(R.drawable.arrow_up);
                    isExpandedText = true;
                }
            }
        });

        holder.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnProductClickListener.onOrderProduct(mProductList.get(position));
            }
        });

        holder.btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnProductClickListener.onSettingProduct(mProductList.get(position));
            }
        });

    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        SliderView sliderView ;
        ImageView imgViewProductBanner;
        TextView txtViewProductName, txtViewProductPrice, txtViewProductDescript;
        Button btnSetting, btnOrder;
        ImageButton btnExpandText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sliderView               = itemView.findViewById(R.id.sliderViewProduct);
            imgViewProductBanner     = itemView.findViewById(R.id.imageViewProductBanner);
            txtViewProductName       = itemView.findViewById(R.id.textViewProductName);
            txtViewProductPrice      = itemView.findViewById(R.id.textViewProductPrice);
            txtViewProductDescript   = itemView.findViewById(R.id.textViewDiscrip);
            btnSetting               = itemView.findViewById(R.id.buttonSettingProduct);
            btnOrder                 = itemView.findViewById(R.id.buttonOrderProduct);
            btnExpandText            = itemView.findViewById(R.id.imageButtonExpandText);
        }
    }

}
