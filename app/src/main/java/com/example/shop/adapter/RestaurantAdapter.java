package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.ultil.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    ArrayList<Restaurant> mRestaurantList;
    Context mContext;

    public RestaurantAdapter(ArrayList<Restaurant> mRestaurantList, Context mContext) {
        this.mRestaurantList = mRestaurantList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_restaurant, parent, false);

        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = mRestaurantList.get(position);

        holder.mTxtViewRestaurantName.setText(restaurant.getRestaurantName());
        holder.mTxtViewRestaurantAddress.setText(restaurant.getRestaurantAddress());

    }

    @Override
    public int getItemCount() {
        return mRestaurantList.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{
        TextView mTxtViewRestaurantAddress, mTxtViewRestaurantName;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtViewRestaurantName      = itemView.findViewById(R.id.textViewRestaurantName);
            mTxtViewRestaurantAddress   = itemView.findViewById(R.id.textViewRestaurantAddress);


        }
    }
}
