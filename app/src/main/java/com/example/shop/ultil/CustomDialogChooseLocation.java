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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomDialogChooseLocation extends Dialog implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    Activity hostActivity;
    Button mBtnChoose;
    Spinner mSpinnerCity;

    public CustomDialogChooseLocation (Activity activity){
        super(activity);
        hostActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_custom_dialog_choose_location);
        mBtnChoose   = findViewById(R.id.buttonChoose);
        mSpinnerCity = findViewById(R.id.spinnerCity);
        mBtnChoose.setOnClickListener(this);

        ArrayList<String> cityList = new ArrayList<>();
        cityList.add("Chọn Tỉnh/Thành Phố");
        cityList.add("Hồ Chí Minh");
        cityList.add("Hà Nội");
        cityList.add("Đà Nẵng");
        cityList.add("Nha Trang");
        cityList.add("Vũng Tàu");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(hostActivity.getBaseContext(),R.layout.layout_selected_city,
                R.id.textViewCity, cityList);

        dataAdapter.setDropDownViewResource(R.layout.layout_spinner_single_line);

        mSpinnerCity.setAdapter(dataAdapter);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(hostActivity.getBaseContext(), "It Works!!!", Toast.LENGTH_LONG).show();
        dismiss();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
