package com.example.shop.dialogs;

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
import com.example.shop.utils.CreateDistrictListHelper;

public class CustomDialogChooseDistrict extends Dialog implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    Context mContext;
    int mCityPosition;
    Button mBtnChoose;
    Spinner mSpinnerDistrict;
    OnSpinnerItemSelectedListener mOnSpinnerItemSelectedListener;
    String[] mDistrictList;

    public CustomDialogChooseDistrict(@NonNull Context mContext, int mCityPosition) {
        super(mContext);
        this.mContext = mContext;
        this.mCityPosition = mCityPosition;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_choose_city);
        mBtnChoose = findViewById(R.id.buttonChoose);
        mSpinnerDistrict = findViewById(R.id.spinnerCity);
        mBtnChoose.setOnClickListener(this);

        mDistrictList = CreateDistrictListHelper.createDistrictList(mContext, mCityPosition);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext, R.layout.layout_selected_city,
                R.id.textViewCity, mDistrictList);

        dataAdapter.setDropDownViewResource(R.layout.layout_spinner_single_line);
        mSpinnerDistrict.setAdapter(dataAdapter);

    }

    @Override
    public void onClick(View v) {
        mOnSpinnerItemSelectedListener.onItemSelected(mSpinnerDistrict.getSelectedItemPosition());
        dismiss();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setOnItemSelectedListener(OnSpinnerItemSelectedListener onSpinnerItemSelectedListener) {
        mOnSpinnerItemSelectedListener = onSpinnerItemSelectedListener;
    }

}
