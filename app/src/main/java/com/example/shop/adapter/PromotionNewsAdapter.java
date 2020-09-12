package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.ultil.DownloadImageTask;
import com.example.shop.ultil.PromotionNews;

import java.util.ArrayList;

public class PromotionNewsAdapter extends RecyclerView.Adapter<PromotionNewsAdapter.PromotionViewHolder> {

    private ArrayList<PromotionNews> mPromotionNewsList;
    private Context context;

    public PromotionNewsAdapter(ArrayList<PromotionNews> mPromotionNewsList, Context context) {
        this.mPromotionNewsList = mPromotionNewsList;
        this.context = context;
    }

    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_promotion_news, parent, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionViewHolder holder, int position) {
        PromotionNews promotionNews = mPromotionNewsList.get(position);

        new DownloadImageTask(holder.mImgViewBannerPromotion)
                .execute(promotionNews.getImageUrl());
        holder.mTxtViewPromotionTitle.setText(promotionNews.getTitle());
        holder.mTxtViewPromotionDescription.setText(promotionNews.getPromotionDescription());
        holder.mTxtViewPeriodPromotion.setText(promotionNews.getPeriodPromotion());
        holder.mTxtViewHeartCount.setText(String.valueOf(promotionNews.getHeartCount()));
        holder.mTxtViewPromotionDescription.setText(promotionNews.getPromotionDescription());
    }

    @Override
    public int getItemCount() {
        return mPromotionNewsList.size();
    }

    public class PromotionViewHolder extends RecyclerView.ViewHolder{

        ImageView mImgViewBannerPromotion;
        TextView mTxtViewPromotionTitle, mTxtViewPeriodPromotion, mTxtViewHeartCount, mTxtViewPromotionDescription;
        Button mBtnPromotion, mBtnSeeMore;
        ImageButton mImgButtonCalendar, mImgButtonHeart;

        public PromotionViewHolder(@NonNull View itemView) {
            super(itemView);

            mImgViewBannerPromotion = itemView.findViewById(R.id.imageViewBannerPromotion);
            mTxtViewPromotionTitle  = itemView.findViewById(R.id.textViewTitle);
            mTxtViewPeriodPromotion = itemView.findViewById(R.id.textViewPeriodPromotion);
            mTxtViewHeartCount      = itemView.findViewById(R.id.textViewHeartCount);
            mTxtViewPromotionDescription = itemView.findViewById(R.id.textViewPromotionDescription);
            mBtnPromotion           = itemView.findViewById(R.id.buttonPromotion);
            mBtnSeeMore             = itemView.findViewById(R.id.buttonSeeMore);
            mImgButtonCalendar      = itemView.findViewById(R.id.imageButtonCalendar);
            mImgButtonHeart         = itemView.findViewById(R.id.imageButtonHeart);
        }
    }
}
