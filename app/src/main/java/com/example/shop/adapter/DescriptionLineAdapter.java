package com.example.shop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.utils.objects.Product;
import com.example.shop.utils.objects.ProductALaCarte;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DescriptionLineAdapter extends RecyclerView.Adapter<DescriptionLineAdapter.ViewHolder> {

    ArrayList<ProductALaCarte> mAlacarteArrList;
    Context context;

    public DescriptionLineAdapter(ArrayList<ProductALaCarte> mAlacarteArrList, Context context) {
        this.mAlacarteArrList = mAlacarteArrList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_alacarte_description, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ProductALaCarte aLaCarte = mAlacarteArrList.get(position);

        if(aLaCarte.isUpgradable()){
            if(aLaCarte.getChosen_upgrade_position() != 0){
                holder.mTxtViewPriceAdded.setVisibility(View.VISIBLE);
                holder.mTxtViewPriceAdded.setText(("+" + aLaCarte.getUpgrades().get(aLaCarte.getChosen_upgrade_position()).getPrice_change()));
            }

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference dataRef = database.getReference()
                                        .child(aLaCarte.getUpgrades()
                                        .get(aLaCarte.getChosen_upgrade_position())
                                                .getProduct());

            dataRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Product product = snapshot.getValue(Product.class);
                    holder.mTxtViewAlacarteName.setText(("* " + aLaCarte.getUpgrades().get(aLaCarte.getChosen_upgrade_position()).getPortion() + " " + product.getFood_name().trim()));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            holder.mTxtViewPriceAdded.setVisibility(View.GONE);
            holder.mTxtViewAlacarteName.setText(("* " + aLaCarte.getChosen_alacarte().trim()));
        }
    }

    @Override
    public int getItemCount() {
        if(mAlacarteArrList != null){
            return mAlacarteArrList.size();
        }else{
            Log.d("EEE", "It's null in Descript Line Adapter");
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTxtViewAlacarteName, mTxtViewPriceAdded;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTxtViewAlacarteName = itemView.findViewById(R.id.textViewAlacarteName);
            mTxtViewPriceAdded   = itemView.findViewById(R.id.textViewPriceAdded);
        }
    }
}
