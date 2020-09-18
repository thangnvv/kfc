package com.example.shop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.shop.R;

public class SettingActivity extends AppCompatActivity{

    ImageButton mImgButtonClose;
    Switch mSwtReceiveNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();

        mImgButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSwtReceiveNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(SettingActivity.this, "Receive Notification is turn on", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SettingActivity.this, "Receive Notification is turn off", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void initView() {
        mImgButtonClose = findViewById(R.id.imageButtonClose);
        mSwtReceiveNotification = findViewById(R.id.switchReceiveNotifi);
    }
}