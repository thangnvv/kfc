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
import com.example.shop.adapter.ALaCarteLineAdapter;

public class CustomDialogChooseALaCarte extends Dialog implements View.OnClickListener, ALaCarteLineAdapter.OnChooseListener {
    TextView mTxtViewTitle;
    ImageButton mImgButtonCancel;
    Button mBtnConfirm;
    RecyclerView recyclerViewALaCarteProduct;
    ALaCarteLineAdapter aLaCarteLineAdapter;
    String choosenName;
    String[] options;
    Context context;
    OnConfirmChoosenFoodName mOnConfirmChoosenFoodName;

    public void setOnConfirmChoosenFoodName (OnConfirmChoosenFoodName mOnConfirmChoosenFoodName){
        this.mOnConfirmChoosenFoodName = mOnConfirmChoosenFoodName;
    }

    public CustomDialogChooseALaCarte(@NonNull Context context, String choosenname, String[] options) {
        super(context);
        this.context = context;
        this.choosenName = choosenname;
        this.options = options;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_change_alacarte_product);

        initView();
        setViewClick();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerViewALaCarteProduct.setLayoutManager(linearLayoutManager);
        recyclerViewALaCarteProduct.setAdapter(aLaCarteLineAdapter);
        aLaCarteLineAdapter.setOnChooseListener(this);
        mTxtViewTitle.setText("MỜI CHỌN 1 TRONG "+  options.length  +" MÓN DƯỚI ĐÂY");
    }

    private void setViewClick() {
        mBtnConfirm.setOnClickListener(this);
        mImgButtonCancel.setOnClickListener(this);
    }

    private void initView() {
        mTxtViewTitle = findViewById(R.id.textViewTitle);
        mImgButtonCancel = findViewById(R.id.imageButtonCancel);
        mBtnConfirm     = findViewById(R.id.buttonConfirm);
        recyclerViewALaCarteProduct = findViewById(R.id.recyclerViewALaCarteProduct);
        aLaCarteLineAdapter = new ALaCarteLineAdapter(context, choosenName, options);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButtonCancel:
                dismiss();
                break;
            case R.id.buttonConfirm:
                mOnConfirmChoosenFoodName.confirmFoodName(choosenName);
                dismiss();
                break;
        }
    }

    @Override
    public void unCheckFoodName(String foodName){
        for(int i  = 0; i < options.length; i++){
            LinearLayout rootLinearLayout = (LinearLayout) recyclerViewALaCarteProduct.getChildAt(i);
            if(rootLinearLayout != null){
                LinearLayout llALaCarteLine = rootLinearLayout.findViewById(R.id.linearLayoutALaCarteLine);
                TextView textName = llALaCarteLine.findViewById(R.id.textViewALaCarteLine);
                if(textName.getText().equals(foodName)){
                    choosenName = foodName;
                }else{
                    LinearLayout llCheckChoose = rootLinearLayout.findViewById(R.id.linearLayoutCheckChoose);
                    llCheckChoose.setVisibility(View.INVISIBLE);
                }
            }

        }
    }

    public interface OnConfirmChoosenFoodName{
        void confirmFoodName(String choosenFoodName);
    }

}
