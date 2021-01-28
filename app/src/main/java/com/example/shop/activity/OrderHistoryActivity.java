package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.shop.R;
import com.example.shop.adapter.OrderHistoryAdapter;
import com.example.shop.objects.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {
    ImageButton mImgButtonClose;
    RecyclerView mRcvOrderHistory;
    LinearLayoutManager mLlManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    OrderHistoryAdapter mOrderHistoryAdapter;
    ArrayList<Order> mOrderedList;
    DatabaseReference mDataRef = FirebaseDatabase.getInstance().getReference();
    FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        mOrderedList = new ArrayList<>();
        mImgButtonClose = findViewById(R.id.imageButtonClose);
        mRcvOrderHistory = findViewById(R.id.recyclerViewOrderHistory);

        mDataRef.child("user").child(mCurrentUser.getUid()).child("order_history").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String orderJson = snapshot.getValue(String.class);
                Order order = gson.fromJson(orderJson, Order.class);
                mOrderedList.add(order);
                mOrderHistoryAdapter.notifyDataSetChanged();
                Log.d("DDD", "In Order History Activity: Date: " + order.getDate() + "; Total: " + order.getTotal());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mOrderHistoryAdapter = new OrderHistoryAdapter(mOrderedList, this);
        mRcvOrderHistory.setLayoutManager(mLlManager);
        mRcvOrderHistory.setAdapter(mOrderHistoryAdapter);

        mImgButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}