package com.example.shop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.ultil.Product;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Product> mProductList;
    private Context context;
    private boolean expandTextStatus = false;
    OnProductClickListener onProductClickListener;

    // This method set callback for fragment
    public void setOnProductClickListener(OnProductClickListener onProductClickListener){
        this.onProductClickListener = onProductClickListener;
    }

    public ProductAdapter(ArrayList<Product> mProductList, Context context) {
        this.mProductList = mProductList;
        this.context = context;
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
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Product product = mProductList.get(position);
        AdapterForSlider adapter = new AdapterForSlider(getContext(), product.getUrlImageBanner() );

        holder.sliderView.setSliderAdapter(adapter);

        holder.txtViewProductName.setText(product.getFoodName());
        holder.txtViewProductPrice.setText(product.getFoodPrice());
        holder.txtViewProductDescript.setText(product.getFoodDescrip());

        holder.btnExpandText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expandTextStatus){
                    holder.txtViewProductDescript.setMaxLines(3);
                    holder.btnExpandText.setImageResource(R.drawable.arrow_down);
                    expandTextStatus = false;
                }else {
                    holder.txtViewProductDescript.setMaxLines(Integer.MAX_VALUE);
                    holder.btnExpandText.setImageResource(R.drawable.arrow_up);
                    expandTextStatus = true;
                }
            }
        });

        holder.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProductClickListener.onOrderProduct(mProductList.get(position));
            }
        });

        holder.btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProductClickListener.onSettingProduct(mProductList.get(position));
                Log.d("EEE", "At button Setting in Product Adapter");
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
        TextView txtViewProductName, txtViewProductPrice, txtViewProductDescript;
        Button btnSetting, btnOrder;
        ImageButton btnExpandText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sliderView               = itemView.findViewById(R.id.sliderViewProduct);
            txtViewProductName       = itemView.findViewById(R.id.textViewProductName);
            txtViewProductPrice      = itemView.findViewById(R.id.textViewProductPrice);
            txtViewProductDescript   = itemView.findViewById(R.id.textViewDiscrip);
            btnSetting               = itemView.findViewById(R.id.buttonSettingProduct);
            btnOrder                 = itemView.findViewById(R.id.buttonOrderProduct);
            btnExpandText            = itemView.findViewById(R.id.imageButtonExpandText);
        }
    }

}
