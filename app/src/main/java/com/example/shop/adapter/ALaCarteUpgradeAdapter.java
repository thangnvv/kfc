package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shop.R;
import com.example.shop.utils.objects.Product;
import com.example.shop.utils.objects.Upgrade;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ALaCarteUpgradeAdapter extends RecyclerView.Adapter<ALaCarteUpgradeAdapter.ViewHolder> {
    ArrayList<Upgrade> upgrades;
    Context context;
    Upgrade chosenUpgrade;
    OnUpgradeChooseListener onUpgradeChooseListener;
    int totalPortion = 0;

    public void setOnUpgradeChooseListener(OnUpgradeChooseListener onUpgradeChooseListener) {
        this.onUpgradeChooseListener = onUpgradeChooseListener;
    }

    public ALaCarteUpgradeAdapter(ArrayList<Upgrade> upgrades, Context context, Upgrade chosenUpgrade) {
        this.upgrades = upgrades;
        this.context = context;
        this.chosenUpgrade = chosenUpgrade;

        for (Upgrade upgrade : upgrades) {
            totalPortion = totalPortion + upgrade.getPortion();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_alacarte_upgrade, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Upgrade upgrade = upgrades.get(position);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference().child(upgrade.getProduct());
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Get Product for product alacarte
                Product product = snapshot.getValue(Product.class);
                Glide.with(context)
                        .load(product.getUrls_banner().get(0))
                        .fitCenter()
                        .into(holder.mImgViewProductImage);
                holder.mTxtViewALaCarteName.setText(product.getFood_name());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Showing text view base on price change
        if (upgrade.getPrice_change().equals("")) {
            holder.mTxtViewPriceChange.setText("Có Sẵn");
        } else {
            holder.mTxtViewPriceChange.setText(("+" + upgrade.getPrice_change()));
        }

        // Showing image button base on alacarte portion
        if (upgrade.getPortion() >=1) {
            holder.mImgButtonUpgrade.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.check, null));
        } else {
            holder.mImgButtonUpgrade.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.uncheck, null));
        }

        // Showing linear layout change portion base on product alacarte portion
        holder.mTxtViewPortion.setText(String.valueOf(upgrade.getPortion()));
    }

    @Override
    public int getItemCount() {
        return upgrades.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgViewProductImage;
        TextView mTxtViewALaCarteName, mTxtViewPriceChange, mTxtViewPortion;
        ImageButton mImgButtonUpgrade, mImgButtonPlus, mImgButtonMinus;
        LinearLayout mLlAlacarte, mLlChangePortion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImgViewProductImage = itemView.findViewById(R.id.imageViewProductImage);
            mTxtViewALaCarteName = itemView.findViewById(R.id.textViewAlacarteName);
            mTxtViewPriceChange = itemView.findViewById(R.id.textViewPriceChange);
            mTxtViewPortion = itemView.findViewById(R.id.textViewPortion);
            mImgButtonUpgrade = itemView.findViewById(R.id.imageButtonUpgrade);
            mImgButtonMinus = itemView.findViewById(R.id.imageButtonMinus);
            mImgButtonPlus = itemView.findViewById(R.id.imageButtonPlus);
            mLlChangePortion = itemView.findViewById(R.id.linearLayoutChangePortion);
            mLlAlacarte = itemView.findViewById(R.id.linearLayoutAlacarte);

            mLlAlacarte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i = 0; i < upgrades.size() ; i++){
                        if(i == getAdapterPosition()){
                            upgrades.get(i).setPortion(1);
                        }else{
                            upgrades.get(i).setPortion(0);
                        }
                    }
                    onUpgradeChooseListener.onChoose(upgrades.get(getAdapterPosition()), getAdapterPosition());
                }
            });

            if(totalPortion >= 2){
                mLlAlacarte.setClickable(false);
                mLlChangePortion.setVisibility(View.VISIBLE);
                mImgButtonPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i = 0; i < upgrades.size() ; i++){
                            if(i == getAdapterPosition()){
                                if(upgrades.get(i).getPortion() < totalPortion){
                                    int portion = upgrades.get(i).getPortion() + 1;
                                    upgrades.get(i).setPortion(portion);
                                    onUpgradeChooseListener.onUpgradeChangePortion();
                                }
                            }else{
                                if(upgrades.get(i).getPortion() > 0){
                                    int portion = upgrades.get(i).getPortion() - 1;
                                    upgrades.get(i).setPortion(portion);
                                }
                            }
                        }
                    }
                });

                mImgButtonMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i = 0; i < upgrades.size() ; i++){
                            if(i == getAdapterPosition()){
                                if(upgrades.get(i).getPortion() > 0){
                                    int portion = upgrades.get(i).getPortion() - 1;
                                    upgrades.get(i).setPortion(portion);
                                    onUpgradeChooseListener.onUpgradeChangePortion();
                                }
                            }else{
                                if(upgrades.get(i).getPortion() < totalPortion){
                                    int portion = upgrades.get(i).getPortion() + 1;
                                    upgrades.get(i).setPortion(portion);
                                }
                            }
                        }
                    }
                });
            }else{
                mLlAlacarte.setClickable(true);
                mLlChangePortion.setVisibility(View.GONE);
            }
        }
    }

    public interface OnUpgradeChooseListener {
        void onChoose(Upgrade upgrade, int position);
        void onUpgradeChangePortion();
    }
}
