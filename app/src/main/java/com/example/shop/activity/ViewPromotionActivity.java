package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shop.R;
import com.example.shop.adapter.RelatedPromotionNewsAdapter;
import com.example.shop.utils.objects.Promotion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewPromotionActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton mImgButtonBack, mImgButtonLike;
    TextView mTxtViewTextTitle, mTxtViewDate, mTxtViewLikeCount, mTxtViewContent;
    ImageView mImgViewPromotionImage;
    RecyclerView mRcvRelatedPromotion;
    Promotion promotion;
    RelatedPromotionNewsAdapter relatedPromotionNewsAdapter;
    LinearLayoutManager mLlManager = new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL, false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_promotion);
        initView();
        setViewClick();
    }

    private void setViewClick() {
        mImgButtonBack.setOnClickListener(this);
        mImgButtonLike.setOnClickListener(this);
    }

    private void initView() {

        Intent intent = getIntent();
        String link = intent.getStringExtra("newsUrl");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = database.getReference().child(link);

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                promotion = snapshot.getValue(Promotion.class);

                relatedPromotionNewsAdapter = new RelatedPromotionNewsAdapter(ViewPromotionActivity.this, promotion.getRelated_news());
                mRcvRelatedPromotion.setLayoutManager(mLlManager);
                mRcvRelatedPromotion.setAdapter(relatedPromotionNewsAdapter);

                relatedPromotionNewsAdapter.setOnClickButtonSeeMoreInRelated(new RelatedPromotionNewsAdapter.OnClickButtonSeeMoreInRelated() {
                    @Override
                    public void onClick(String newsUrl) {
                        Intent intentViewNewsFromRelated = new Intent(ViewPromotionActivity.this, ViewPromotionActivity.class);
                        intentViewNewsFromRelated.putExtra("newsUrl", newsUrl);
                        startActivity(intentViewNewsFromRelated);
                    }
                });

                mTxtViewTextTitle.setText(promotion.getTitle());
                mTxtViewLikeCount.setText(String.valueOf(promotion.getLikes()));
                mTxtViewContent.setText(promotion.getContent().getText());
                mTxtViewDate.setText(promotion.getDate());

                Glide.with(ViewPromotionActivity.this)
                        .load(promotion.getContent().getImage())
                        .fitCenter()
                        .into(mImgViewPromotionImage);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mImgButtonBack         = findViewById(R.id.imageButtonBack);
        mImgButtonLike         = findViewById(R.id.imageButtonLike);
        mTxtViewTextTitle      = findViewById(R.id.textViewTitle);
        mTxtViewDate           = findViewById(R.id.textViewDate);
        mTxtViewLikeCount      = findViewById(R.id.textViewLikeCount);
        mTxtViewContent        = findViewById(R.id.textViewContent);
        mImgViewPromotionImage = findViewById(R.id.imageViewPromotionImage);
        mRcvRelatedPromotion   = findViewById(R.id.recyclerViewRelatedPromotion);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButtonBack :
                finish();
                break;
            case R.id.imageButtonLike :
                if(!promotion.isLike_clicked()){
                    promotion.setLikes(promotion.getLikes() + 1);
                    promotion.setLike_clicked(true);
                }
                break;
        }
    }
}