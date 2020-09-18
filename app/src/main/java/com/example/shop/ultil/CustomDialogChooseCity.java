package com.example.shop.ultil;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.interfaces.OnSpinnerItemSelectedListener;

public class CustomDialogChooseCity extends Dialog implements View.OnClickListener,
        AdapterView.OnItemSelectedListener{

    Activity hostActivity;
    Button mBtnChoose;
    Spinner mSpinnerCity;
    OnSpinnerItemSelectedListener mListener;

    public CustomDialogChooseCity(Activity activity){
        super(activity);
        hostActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_custom_dialog_choose_city);
        mBtnChoose   = findViewById(R.id.buttonChoose);
        mSpinnerCity = findViewById(R.id.spinnerCity);
        mBtnChoose.setOnClickListener(this);

        String[] city = hostActivity.getResources().getStringArray(R.array.city);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(hostActivity.getBaseContext(),R.layout.layout_selected_city,
                R.id.textViewCity, city);

        dataAdapter.setDropDownViewResource(R.layout.layout_spinner_single_line);
        mSpinnerCity.setAdapter(dataAdapter);

    }

    @Override
    public void onClick(View v) {
        mListener.onItemSelectedListener(mSpinnerCity.getSelectedItem().toString());
        Toast.makeText(hostActivity.getBaseContext(), "" + mSpinnerCity.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        dismiss();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setOnItemSelectedListener(OnSpinnerItemSelectedListener listener){
        mListener = listener;
    }

}
