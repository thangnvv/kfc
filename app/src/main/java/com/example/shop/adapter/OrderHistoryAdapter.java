package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.objects.Order;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>{

    ArrayList<Order> mOrderedList;
    Context context;

    public OrderHistoryAdapter(ArrayList<Order> mOrderedList, Context context) {
        this.mOrderedList = mOrderedList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = mOrderedList.get(position);

        OrderLineAdapter orderLineAdapter = new OrderLineAdapter(order.getListProduct(), context);
        LinearLayoutManager mLlManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.mRcvProductList.setLayoutManager(mLlManager);
        holder.mRcvProductList.setAdapter(orderLineAdapter);
        holder.mTxtViewOrderedDate.setText(order.getDate());
        holder.mTxtViewOrderedTotal.setText(order.getTotal());
        holder.mTxtViewShippingFee.setText(order.getShippingFee());
    }

    @Override
    public int getItemCount() {
        return mOrderedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTxtViewOrderedDate, mTxtViewOrderedTotal, mTxtViewShippingFee;
        RecyclerView mRcvProductList;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTxtViewOrderedDate  = itemView.findViewById(R.id.textViewOrderedDate);
            mTxtViewOrderedTotal = itemView.findViewById(R.id.textViewOrderedTotal);
            mTxtViewShippingFee  = itemView.findViewById(R.id.textViewShippingFee);
            mRcvProductList      = itemView.findViewById(R.id.recyclerViewProductList);

        }
    }
}
