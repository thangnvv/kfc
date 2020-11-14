package com.example.shop.ultil;

import java.util.ArrayList;

public class Promotion {

    private String banner;
    private String link;
    private String descript;
    private PromotionContent content;
    private String date;
    private int likes;
    private String title;
    private ArrayList<RelatedNews> related_news;
    private boolean like_clicked;
    public void Promotion(){}

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescript() {
        return descript;
    }

    public boolean isLike_clicked() {
        return like_clicked;
    }

    public void setLike_clicked(boolean like_clicked) {
        this.like_clicked = like_clicked;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public PromotionContent getContent() {
        return content;
    }

    public void setContent(PromotionContent content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<RelatedNews> getRelated_news() {
        return related_news;
    }

    public void setRelated_news(ArrayList<RelatedNews> related_news) {
        this.related_news = related_news;
    }
}
