package com.example.shop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shop.R;
import com.example.shop.ultil.CustomDialogChooseALaCarte;
import com.example.shop.ultil.CustomDialogUpgradeALaCarte;
import com.example.shop.ultil.Product;
import com.example.shop.ultil.ProductALaCarte;
import com.example.shop.ultil.ProductSeperated;
import com.example.shop.ultil.Upgrade;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SeperateProductAdapter extends RecyclerView.Adapter<SeperateProductAdapter.ViewHolder> {

    ArrayList<ProductSeperated> mProductSeperateds;
    ArrayList<ProductALaCarte> mProductALacartes;
    Context context;
    CustomDialogChooseALaCarte customDialogChooseALaCarte;
    CustomDialogUpgradeALaCarte customDialogUpgradeALaCarte;
    Boolean upgradable;

    public SeperateProductAdapter(ArrayList<ProductALaCarte> mProductALacartes, ArrayList<ProductSeperated> mProductSeperateds, Context context) {
        this.mProductALacartes = mProductALacartes;
        this.mProductSeperateds = mProductSeperateds;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_seperated_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ProductALaCarte productALaCarte = mProductALacartes.get(position);

        if (!productALaCarte.isUpgradable()) {
            // This alacarte only for change option of alacarte
            final ProductSeperated productSeperated = mProductSeperateds.get(position);
            holder.mTxtViewName.setText(productALaCarte.getChosen_alacarte());
            holder.mImgViewProductImage.setVisibility(View.GONE);
            if (productSeperated.getOptions().length <= 1) {
                //this alacarte has no option and no upgrade
                holder.mLLChangeProduct.setVisibility(View.GONE);
            } else {
                // it only mutable
                holder.mLLChangeProduct.setVisibility(View.VISIBLE);
            }
            upgradable = false;
        } else {
            // Get and load image for upgrade product
            upgradable = true;
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference().child(productALaCarte.getUpgrades().get(productALaCarte.getChosen_upgrade_position()).getProduct());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Product product = snapshot.getValue(Product.class);
                    Glide.with(context)
                            .load(product.getUrls_banner().get(0))
                            .fitCenter()
                            .into(holder.mImgViewProductImage);
                    holder.mImgViewProductImage.setVisibility(View.VISIBLE);
                    holder.mTxtViewName.setText((productALaCarte.getUpgrades().get(productALaCarte.getChosen_upgrade_position()).getPortion() + " " + product.getFood_name()));

                    if (productALaCarte.getUpgrades().get(productALaCarte.getChosen_upgrade_position()).getPrice_change().equals("")) {
                        holder.mTxtViewPriceAdded.setVisibility(View.GONE);
                        holder.mTxtViewPriceAdded.setText((""));
                    } else {
                        holder.mTxtViewPriceAdded.setVisibility(View.VISIBLE);
                        holder.mTxtViewPriceAdded.setText(("+" + productALaCarte.getUpgrades().get(productALaCarte.getChosen_upgrade_position()).getPrice_change()));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });

            if(productALaCarte.getUpgrades().size() > 1){
                holder.mLLChangeProduct.setVisibility(View.VISIBLE);
            }else{
                holder.mLLChangeProduct.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mProductALacartes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtViewName, mTxtViewChange, mTxtViewPriceAdded;
        ImageButton mImgButtonChange;
        LinearLayout mLLChangeProduct;
        ImageView mImgViewProductImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTxtViewChange = itemView.findViewById(R.id.textViewChange);
            mTxtViewName = itemView.findViewById(R.id.textViewName);
            mTxtViewPriceAdded = itemView.findViewById(R.id.textViewPriceAdded);
            mImgButtonChange = itemView.findViewById(R.id.imageButtonChange);
            mLLChangeProduct = itemView.findViewById(R.id.linearLayoutChangeProduct);
            mImgViewProductImage = itemView.findViewById(R.id.imageViewProductImage);

            mLLChangeProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProductALaCarte productALaCarte = mProductALacartes.get(getAdapterPosition());

                    if (productALaCarte.isUpgradable()) {
                        // Showing upgrade options

                        customDialogUpgradeALaCarte = new CustomDialogUpgradeALaCarte(context, productALaCarte.getUpgrades()
                                , productALaCarte.getChosen_upgrade_position());
                        customDialogUpgradeALaCarte.setCancelable(false);
                        customDialogUpgradeALaCarte.show();

                        customDialogUpgradeALaCarte.setOnConfirmChosenUpgrade(new CustomDialogUpgradeALaCarte.OnConfirmChosenUpgrade() {
                            @Override
                            public void confirmUpgrade(final Upgrade chosenUpgrade, final int positionUpgrade, final int priceChange) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference databaseReference = database.getReference().child(chosenUpgrade.getProduct());
                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Product product = snapshot.getValue(Product.class);

                                        Glide.with(context)
                                                .load(product.getUrls_banner().get(0))
                                                .fitCenter()
                                                .into(mImgViewProductImage);
                                        mTxtViewName.setText(product.getFood_name());

                                        if (!chosenUpgrade.getPrice_change().equals("")) {
                                            mTxtViewPriceAdded.setVisibility(View.VISIBLE);
                                            mTxtViewPriceAdded.setText(("+" + chosenUpgrade.getPrice_change()));
                                            onAlacarteUpgraded.onUpgraded(priceChange);
                                        } else {
                                            mTxtViewPriceAdded.setVisibility(View.GONE);
                                            mTxtViewPriceAdded.setText((""));
                                            onAlacarteUpgraded.onUpgraded(priceChange);
                                        }
                                        mProductALacartes.get(getAdapterPosition()).setChosen_upgrade_position(positionUpgrade);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }

                            @Override
                            public void confirmChooseMultipleUpgrade(ArrayList<Upgrade> upgradeList) {
                               onAlacarteUpgraded.onMultipleUpgrade(upgradeList, mProductALacartes.get(getAdapterPosition()));
                            }
                        });
                    } else {
                        // Showing options
                        final ProductSeperated productSeperated = mProductSeperateds.get(getAdapterPosition());

                        customDialogChooseALaCarte = new CustomDialogChooseALaCarte(context, productALaCarte.getChosen_alacarte(), productSeperated.getOptions());
                        customDialogChooseALaCarte.setCancelable(false);
                        customDialogChooseALaCarte.show();
                        customDialogChooseALaCarte.setOnConfirmChosenFoodName(new CustomDialogChooseALaCarte.OnConfirmChosenFoodName() {
                            @Override
                            public void confirmFoodName(String chosenFoodName) {
                                mTxtViewName.setText(chosenFoodName);
                                onAlacarteUpgraded.onChangeOption(productSeperated.getChosenName(), chosenFoodName);
                                productSeperated.setChoosenName(chosenFoodName);
                                productALaCarte.setChosen_alacarte(chosenFoodName);
                            }
                        });
                    }
                }
            });

        }
    }

    OnAlacarteUpgraded onAlacarteUpgraded;

    public void setOnAlacarteUpgraded(OnAlacarteUpgraded onAlacarteUpgraded) {
        this.onAlacarteUpgraded = onAlacarteUpgraded;
    }

    public interface OnAlacarteUpgraded {
        void onUpgraded(int priceChanged);
        void onChangeOption(String lastChosen, String newChosen);
        void onMultipleUpgrade(ArrayList<Upgrade> upgradeList, ProductALaCarte aLaCarte);
    }
}
