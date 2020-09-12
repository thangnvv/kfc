package com.example.shop.ultil;

public class PromotionNews {

    private String imageUrl, title, periodPromotion, promotionDescription;
    private int heartCount;

    public PromotionNews(String imageUrl, String title, String periodPromotion, String promotionDescription, int heartCount) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.periodPromotion = periodPromotion;
        this.promotionDescription = promotionDescription;
        this.heartCount = heartCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeriodPromotion() {
        return periodPromotion;
    }

    public void setPeriodPromotion(String periodPromotion) {
        this.periodPromotion = periodPromotion;
    }

    public String getPromotionDescription() {
        return promotionDescription;
    }

    public void setPromotionDescription(String promotionDescription) {
        this.promotionDescription = promotionDescription;
    }

    public int getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }
}
