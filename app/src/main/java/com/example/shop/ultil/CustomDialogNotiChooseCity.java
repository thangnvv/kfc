package com.example.shop.ultil;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.shop.R;

public class CustomDialogNotiChooseCity extends Dialog {

    Button mBtnChoose;
    Context context;

    public CustomDialogNotiChooseCity(@NonNull Context context) {
        super(context);
        this.context = context;
    }

//    public CustomDialogChooseCity(Activity activity){
//        super(activity);
//        hostActivity = activity;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_noti_choose_city);
        mBtnChoose   = findViewById(R.id.buttonChoose);
        mBtnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }



}
