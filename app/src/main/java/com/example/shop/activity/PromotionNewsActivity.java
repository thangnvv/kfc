package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.adapter.PromotionNewsAdapter;
import com.example.shop.ultil.Promotion;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PromotionNewsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    ArrayList<Promotion> mListPromotion;
    PromotionNewsAdapter mPromotionNewsAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerViewPromotionNews;
    BottomNavigationView mBtmNavigationView;
    private boolean doubleBackToExitPressedOnce;
    private final Handler mHandlerQuiteApp = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_news);

        intiView();
        addPromotionNews();

        mBtmNavigationView.setSelectedItemId(R.id.promotion);
        mBtmNavigationView.setOnNavigationItemSelectedListener(this);

        mPromotionNewsAdapter      = new PromotionNewsAdapter(getApplicationContext(), mListPromotion);
        mLayoutManager             = new LinearLayoutManager(getApplicationContext());
        mRecyclerViewPromotionNews.setLayoutManager(mLayoutManager);
        mRecyclerViewPromotionNews.setAdapter(mPromotionNewsAdapter);

        mPromotionNewsAdapter.setOnClickButton(new PromotionNewsAdapter.OnClickButton() {
            @Override
            public void onClickSeeMore(int position) {
                Intent intentSeePromotionNews = new Intent(PromotionNewsActivity.this, ViewPromotionActivity.class);
                intentSeePromotionNews.putExtra("newsUrl", mListPromotion.get(position).getLink());
                startActivity(intentSeePromotionNews);
            }

            @Override
            public void onClickLike() {
                mPromotionNewsAdapter.notifyDataSetChanged();
            }
        });
    }

    private void intiView() {
        mListPromotion = new ArrayList<>();
        mRecyclerViewPromotionNews = findViewById(R.id.recyclerViewPromotionNews);
        mBtmNavigationView = findViewById(R.id.bottomNavigationView);

    }

    private void addPromotionNews() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = database.getReference().child("promotion_news");

        dataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                mListPromotion.add(snapshot.getValue(Promotion.class));
                if(mPromotionNewsAdapter!= null){
                    mPromotionNewsAdapter.notifyDataSetChanged();
                }
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

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more:
                Intent intentMore = new Intent(PromotionNewsActivity.this, MoreActivity.class);
                startActivity(intentMore);
                finish();
                break;
            case R.id.menu:
                Intent intentMenu = new Intent(PromotionNewsActivity.this, MainActivity.class);
                startActivity(intentMenu);
                finish();
                break;
            case R.id.restaurant:
                Intent intentRestaurant = new Intent(PromotionNewsActivity.this, RestaurantActivity.class);
                startActivity(intentRestaurant);
                finish();
        }

        return true;
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn thêm lần nữa để thoát App", Toast.LENGTH_SHORT).show();

        mHandlerQuiteApp.postDelayed(mRunnable, 2000);
    }

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandlerQuiteApp != null) { mHandlerQuiteApp.removeCallbacks(mRunnable); }
    }
}