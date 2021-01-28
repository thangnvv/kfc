package com.example.shop.activity;

import androidx.annotation.NonNull;
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
import com.example.shop.dialogs.CustomDialogLoading;
import com.example.shop.objects.Promotion;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PromotionNewsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Promotion> mListPromotion;
    private PromotionNewsAdapter mPromotionNewsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerViewPromotionNews;
    private BottomNavigationView mBtmNavigationView;
    private boolean doubleBackToExitPressedOnce;
    private final Handler mHandlerQuiteApp = new Handler();
    private CustomDialogLoading dialogLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_news);
        intiView();
        showLoadingDialog();
        addPromotionNews();

        mBtmNavigationView.setSelectedItemId(R.id.promotion);
        mBtmNavigationView.setOnNavigationItemSelectedListener(this);

        mPromotionNewsAdapter = new PromotionNewsAdapter(getApplicationContext(), mListPromotion);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
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

    private void showLoadingDialog() {
        dialogLoading = new CustomDialogLoading();
        dialogLoading.setCancelable(false);
        dialogLoading.show(this.getSupportFragmentManager(), "Loading Dialog In Promotion News");
    }

    private void intiView() {
        mListPromotion = new ArrayList<>();
        mRecyclerViewPromotionNews = findViewById(R.id.recyclerViewPromotionNews);
        mBtmNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void addPromotionNews() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = database.getReference().child("promotion_news");

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    mListPromotion.add(dataSnapshot.getValue(Promotion.class));
                }
                if (mPromotionNewsAdapter != null) {
                    mPromotionNewsAdapter.notifyDataSetChanged();
                    dialogLoading.dismiss();
                }
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
        if (mHandlerQuiteApp != null) {
            mHandlerQuiteApp.removeCallbacks(mRunnable);
        }
    }
}