package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.shop.R;
import com.example.shop.adapter.PromotionNewsAdapter;
import com.example.shop.ultil.PromotionNews;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class PromotionNewsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    ArrayList<PromotionNews> mListPromotionNews;
    PromotionNewsAdapter mPromotionNewsAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerViewPromotionNews;
    BottomNavigationView mBtmNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_news);

        intiView();
        addPromotionNews();

        mBtmNavigationView.setSelectedItemId(R.id.promotion);
        mBtmNavigationView.setOnNavigationItemSelectedListener(this);

        mPromotionNewsAdapter      = new PromotionNewsAdapter(mListPromotionNews, getApplicationContext());
        mLayoutManager             = new LinearLayoutManager(getApplicationContext());
        mRecyclerViewPromotionNews.setLayoutManager(mLayoutManager);
        mRecyclerViewPromotionNews.setAdapter(mPromotionNewsAdapter);
    }

    private void intiView() {

        mListPromotionNews = new ArrayList<>();
        mRecyclerViewPromotionNews = findViewById(R.id.recyclerViewPromotionNews);
        mBtmNavigationView = findViewById(R.id.bottomNavigationView);

    }

    private void addPromotionNews() {

        mListPromotionNews.add(new PromotionNews("https://kfcvietnam.com.vn/uploads/content/a9e4b466583e753c07ddb8d2042b8b6f.png",
                "“Giá Siêu Ưu Đãi” chỉ 18k/1 miếng gà đã trở lại!", "08/09/2020 - 22/10/2020", "Hàng ngàn miếng gà giòn tan nóng hổi chỉ 18K đang chờ bạn đến “măm” tại tất cả nhà hàng KFC từ Thứ 3 đến...", 142) );
        mListPromotionNews.add(new PromotionNews("https://kfcvietnam.com.vn/uploads/content/4513b5869d20f3dd956f2c7ab6b6ea68.png",
                "Gà Phủ Phê - Giá Miễn Chê!!!", "04/09/2020 - 25/10/2020", "Cơ hội ngồi nhà măm gà KFC phủ phê với giá miễn chê suốt tuần đã đến!!!", 184) );
        mListPromotionNews.add(new PromotionNews("https://kfcvietnam.com.vn/uploads/content/9170c92e6a54a3789d19a1e947e7df39.png",
                "Trưa Nay Ăn Gì? Thực Đơn Bữa Trưa Tiết Kiệm chỉ từ 35.000đ/phần.", "18/05/2020 - 31/12/2020", "Hãy ghé ngay các nhà hàng KFC để thưởng thức thực đơn Bữa Trưa Tiết...", 641) );
        mListPromotionNews.add(new PromotionNews("https://kfcvietnam.com.vn/uploads/content/a122806e86b4daa89fd21fbdc77971fe.png",
                "Ngon tuyệt đỉnh từ Trà Sữa Bạc Hà", "02/07/2020 - 31/12/2020", "Sau 1 thời gian chờ đợi, cuối cùng KFC cũng trình làng món Trà Sữa Bạc Hà ngon tuyệt đỉnh. Nước trà được làm từ", 157) );
        mListPromotionNews.add(new PromotionNews("https://kfcvietnam.com.vn/uploads/content/a6e1db2b6df45c9e7960d4b0a7877ccd.png",
                "Thanh Bí Phô-mai – Thơm Bùi Giòn Vị!!!", "01/06/2020 - 31/12/2020", "Món “Ăn Vặt” thần thánh đã xuất hiện tại KFC, cơ hội thưởng thức món ăn có 1-0-2 cho team nhà mình!!!", 813) );

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
}