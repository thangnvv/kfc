package com.example.shop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.shop.R;

public class ContactActivity extends AppCompatActivity {
    ImageButton mImgButtonClose;
    private boolean doubleBackToExitPressedOnce;
    private Handler mHandlerQuiteApp = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mImgButtonClose = findViewById(R.id.imageButtonClose);
        mImgButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
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