package com.example.shop.ultil;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.adapter.ALaCarteOptionsAdapter;

public class CustomDialogChooseALaCarte extends Dialog implements View.OnClickListener, ALaCarteOptionsAdapter.OnChooseListener {
    TextView mTxtViewTitle;
    ImageButton mImgButtonCancel;
    Button mBtnConfirm;
    RecyclerView rvALaCarteProductOptions;
    ALaCarteOptionsAdapter aLaCarteOptionsAdapter;
    String chosenName;
    String[] options;
    Context context;
    OnConfirmChosenFoodName mOnConfirmChosenFoodName;

    public void setOnConfirmChosenFoodName(OnConfirmChosenFoodName mOnConfirmChosenFoodName){
        this.mOnConfirmChosenFoodName = mOnConfirmChosenFoodName;
    }

    public CustomDialogChooseALaCarte(@NonNull Context context, String choosenname, String[] options) {
        super(context);
        this.context = context;
        this.chosenName = choosenname;
        this.options = options;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_change_alacarte_options);

        initView();
        setViewClick();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvALaCarteProductOptions.setLayoutManager(linearLayoutManager);
        rvALaCarteProductOptions.setAdapter(aLaCarteOptionsAdapter);
        aLaCarteOptionsAdapter.setOnChooseListener(this);
        mTxtViewTitle.setText(("MỜI CHỌN 1 TRONG "+  options.length  +" MÓN DƯỚI ĐÂY"));
    }

    private void setViewClick() {
        mBtnConfirm.setOnClickListener(this);
        mImgButtonCancel.setOnClickListener(this);
    }

    private void initView() {
        mTxtViewTitle = findViewById(R.id.textViewTitle);
        mImgButtonCancel = findViewById(R.id.imageButtonCancel);
        mBtnConfirm     = findViewById(R.id.buttonConfirm);
        rvALaCarteProductOptions = findViewById(R.id.recyclerViewALaCarteOptions);
        aLaCarteOptionsAdapter = new ALaCarteOptionsAdapter(context, chosenName, options);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButtonCancel:
                dismiss();
                break;
            case R.id.buttonConfirm:
                mOnConfirmChosenFoodName.confirmFoodName(chosenName);
                dismiss();
                break;
        }
    }

    @Override
    public void unCheckFoodName(String foodName){
        for(int i  = 0; i < options.length; i++){
            LinearLayout rootLinearLayout = (LinearLayout) rvALaCarteProductOptions.getChildAt(i);
            if(rootLinearLayout != null){
                LinearLayout llALaCarteLine = rootLinearLayout.findViewById(R.id.linearLayoutALaCarteLine);
                TextView textName = llALaCarteLine.findViewById(R.id.textViewALaCarteLine);
                if(textName.getText().equals(foodName)){
                    chosenName = foodName;
                }else{
                    LinearLayout llCheckChoose = rootLinearLayout.findViewById(R.id.linearLayoutCheckChoose);
                    llCheckChoose.setVisibility(View.INVISIBLE);
                }
            }

        }
    }

    public interface OnConfirmChosenFoodName {
        void confirmFoodName(String choosenFoodName);
    }

}
