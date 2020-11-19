package com.example.shop.utils.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.example.shop.R;
import com.example.shop.interfaces.OnSpinnerItemSelectedListener;

public class CustomDialogChooseCity extends Dialog implements View.OnClickListener,
        AdapterView.OnItemSelectedListener{

    Context mContext;
    Button mBtnChoose;
    Spinner mSpinnerCity;
    OnSpinnerItemSelectedListener mOnSpinnerItemSelectedListener;

    public CustomDialogChooseCity(@NonNull Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_choose_city);
        mBtnChoose   = findViewById(R.id.buttonChoose);
        mSpinnerCity = findViewById(R.id.spinnerCity);
        mBtnChoose.setOnClickListener(this);

        String[] city = mContext.getResources().getStringArray(R.array.city);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext,R.layout.layout_selected_city,
                R.id.textViewCity, city);

        dataAdapter.setDropDownViewResource(R.layout.layout_spinner_single_line);
        mSpinnerCity.setAdapter(dataAdapter);

    }

    @Override
    public void onClick(View v) {
        mOnSpinnerItemSelectedListener.onItemSelected(mSpinnerCity.getSelectedItemPosition());
        dismiss();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setOnItemSelectedListener(OnSpinnerItemSelectedListener onSpinnerItemSelectedListener){
        mOnSpinnerItemSelectedListener = onSpinnerItemSelectedListener;
    }

}
