package com.example.shop.ultil;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.shop.R;
import com.example.shop.interfaces.OnSpinnerItemSelectedListener;

public class CustomDialogChooseDistrict extends Dialog implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    Context context;
    int cityPosition;
    Button mBtnChoose;
    Spinner mSpinnerDistrict;
    OnSpinnerItemSelectedListener mListener;
    String[] districtList;

    public CustomDialogChooseDistrict(@NonNull Context context, int cityPosition) {
        super(context);
        this.context = context;
        this.cityPosition = cityPosition;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_choose_city);
        mBtnChoose = findViewById(R.id.buttonChoose);
        mSpinnerDistrict = findViewById(R.id.spinnerCity);
        mBtnChoose.setOnClickListener(this);

        districtList = CreateDistrictList.createDistrictList(context , cityPosition);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, R.layout.layout_selected_city,
                R.id.textViewCity, districtList);

        dataAdapter.setDropDownViewResource(R.layout.layout_spinner_single_line);
        mSpinnerDistrict.setAdapter(dataAdapter);

    }

    @Override
    public void onClick(View v) {
        mListener.onItemSelectedListener(mSpinnerDistrict.getSelectedItemPosition());
        Toast.makeText(context, "" + mSpinnerDistrict.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        dismiss();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setOnItemSelectedListener(OnSpinnerItemSelectedListener listener) {
        mListener = listener;
    }

}
