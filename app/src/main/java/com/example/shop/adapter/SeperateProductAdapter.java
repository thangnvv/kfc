package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.ultil.CustomDialogChooseALaCarte;
import com.example.shop.ultil.ProductSeperated;

import java.util.ArrayList;

public class SeperateProductAdapter extends RecyclerView.Adapter<SeperateProductAdapter.ViewHolder>{

    ArrayList<ProductSeperated> mArrListName;
    Context context;
    CustomDialogChooseALaCarte customDialogChooseALaCarte;

    public SeperateProductAdapter(ArrayList<ProductSeperated> mArrListName, Context context) {
        this.mArrListName = mArrListName;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_seperated_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ProductSeperated productSeperated = mArrListName.get(position);

        holder.mTxtViewName.setText(productSeperated.getChoosenName());
        holder.mLLChangeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogChooseALaCarte = new CustomDialogChooseALaCarte(context, productSeperated.getChoosenName(),productSeperated.getOptions());
                customDialogChooseALaCarte.setCancelable(false);
                customDialogChooseALaCarte.show();
                customDialogChooseALaCarte.setOnConfirmChoosenFoodName(new CustomDialogChooseALaCarte.OnConfirmChoosenFoodName() {
                    @Override
                    public void confirmFoodName(String choosenFoodName) {
                        holder.mTxtViewName.setText(choosenFoodName);
                        productSeperated.setChoosenName(choosenFoodName);
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return mArrListName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTxtViewName, mTxtViewChange;
        ImageButton mImgButtonChange;
        LinearLayout mLLChangeProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTxtViewChange = itemView.findViewById(R.id.textViewChange);
            mTxtViewName   = itemView.findViewById(R.id.textViewName);
            mImgButtonChange = itemView.findViewById(R.id.imageButtonChange);
            mLLChangeProduct = itemView.findViewById(R.id.linearLayoutChangeProduct);
        }
    }
}
