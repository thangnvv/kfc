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
import com.example.shop.utils.DownloadImageTask;
import com.example.shop.objects.Promotion;

import java.util.ArrayList;

public class PromotionNewsAdapter extends RecyclerView.Adapter<PromotionNewsAdapter.PromotionViewHolder> {

    private ArrayList<Promotion> mPromotionList;
    private Context context;

    public PromotionNewsAdapter(Context context, ArrayList<Promotion> mPromotionList){
        this.context = context;
        this.mPromotionList = mPromotionList;
    }

    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_promotion_news, parent, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionViewHolder holder, int position) {
        Promotion promotion = mPromotionList.get(position);

        new DownloadImageTask(holder.mImgViewBannerPromotion)
                .execute(promotion.getBanner());
        holder.mTxtViewPromotionTitle.setText(promotion.getTitle());
        holder.mTxtViewPromotionDescription.setText(promotion.getDescript());
        holder.mTxtViewPeriodPromotion.setText(promotion.getDate());
        holder.mTxtViewHeartCount.setText(String.valueOf(promotion.getLikes()));

        if(promotion.isLike_clicked()){
            holder.mImgButtonHeart.setImageResource(R.drawable.heart_red);
        }else{
            holder.mImgButtonHeart.setImageResource(R.drawable.heart);
        }
    }

    @Override
    public int getItemCount() {
            return mPromotionList.size();
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

            mBtnSeeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickButton.onClickSeeMore(getAdapterPosition());
                }
            });

            mImgButtonHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!mPromotionList.get(getAdapterPosition()).isLike_clicked()){
                        mImgButtonHeart.setImageResource(R.drawable.heart_red);
                        int currentLike =  mPromotionList.get(getAdapterPosition()).getLikes();
                        mPromotionList.get(getAdapterPosition()).setLikes(currentLike + 1);
                        mPromotionList.get(getAdapterPosition()).setLike_clicked(true);
                        onClickButton.onClickLike();
                    }
                }
            });
        }
    }

    OnClickButton onClickButton;

    public void setOnClickButton(OnClickButton onClickButton){
        this.onClickButton = onClickButton;
    }

    public interface OnClickButton {
        void onClickSeeMore(int position);
        void onClickLike();
    }
}

