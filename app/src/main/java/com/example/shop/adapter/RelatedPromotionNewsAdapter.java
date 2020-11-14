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

import com.bumptech.glide.Glide;
import com.example.shop.R;
import com.example.shop.ultil.DownloadImageTask;
import com.example.shop.ultil.Promotion;
import com.example.shop.ultil.RelatedNews;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class RelatedPromotionNewsAdapter extends RecyclerView.Adapter<RelatedPromotionNewsAdapter.PromotionViewHolder> {

    private ArrayList<RelatedNews> mRelatedNewsList;
    private Context context;

    public RelatedPromotionNewsAdapter(Context context, ArrayList<RelatedNews> mRelatedNewsList){
        this.context = context;
        this.mRelatedNewsList = mRelatedNewsList;
    }

    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_related_promotion_news, parent, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionViewHolder holder, int position) {
        RelatedNews relatedNews = mRelatedNewsList.get(position);

//        holder.mImgViewBannerPromotion;
        Glide.with(context)
                .load(relatedNews.getBanner())
                .fitCenter()
                .into(holder.mImgViewBannerPromotion);
        holder.mTxtViewPromotionTitle.setText(relatedNews.getTitle());
        holder.mTxtViewDate.setText(relatedNews.getDate());
    }

    @Override
    public int getItemCount() {
            return mRelatedNewsList.size();
    }

    public class PromotionViewHolder extends RecyclerView.ViewHolder{

        ImageView mImgViewBannerPromotion;
        TextView mTxtViewPromotionTitle, mTxtViewDate, mTxtViewSeeMore;

        public PromotionViewHolder(@NonNull View itemView) {
            super(itemView);

            mImgViewBannerPromotion = itemView.findViewById(R.id.imageViewBannerPromotion);
            mTxtViewPromotionTitle  = itemView.findViewById(R.id.textViewTitle);
            mTxtViewDate = itemView.findViewById(R.id.textViewDate);
            mTxtViewSeeMore = itemView.findViewById(R.id.textViewSeeMore);

            mTxtViewSeeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickButtonSeeMoreInRelated.onClick(mRelatedNewsList.get(getAdapterPosition()).getLink());
                }
            });
        }
    }

    OnClickButtonSeeMoreInRelated onClickButtonSeeMoreInRelated;

    public void setOnClickButtonSeeMoreInRelated(OnClickButtonSeeMoreInRelated onClickButtonSeeMoreInRelated){
        this.onClickButtonSeeMoreInRelated = onClickButtonSeeMoreInRelated;
    }

    public interface OnClickButtonSeeMoreInRelated{
        void onClick(String newsUrl);
    }
}

