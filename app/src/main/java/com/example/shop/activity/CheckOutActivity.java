package com.example.shop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.adapter.OrderLineAdapter;
import com.example.shop.objects.Cart;
import com.example.shop.utils.CommonMethodHolder;
import com.example.shop.utils.CreateDistrictListHelper;
import com.example.shop.utils.CreateHtmlTextHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.orhanobut.hawk.Hawk;

import java.util.Calendar;

public class CheckOutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Cart cart = Hawk.get("cart");
    int intTotal;
    String stringTotal;
    TextView mTxtViewCurrentTime, mTxtViewShippingDateTitle, mTxtViewShippingDate, mTxtViewShippingTimeTitle, mTxtViewShippingTime,
            mTxtViewOrderTotal, mTxtViewShippingFee, mTxtViewGrandTotalRed, mTxtViewGrandTotalBlack;
    TextInputLayout mTxtInputLayoutName, mTxtInputLayoutPhone, mTxtInputLayoutEmail, mTxtInputLayoutAddress;
    TextInputEditText mTxtInputEdtTextName, mTxtInputEdtTextPhone, mTxtInputEdtTextEmail, mTxtInputEdtTextAddress;
    Button mBtnGoBack, mBtnNext;
    ImageButton mImgButtonBack;
    RecyclerView mRcvOrderSummary;
    Spinner mSpinnerChooseCity, mSpinnerChooseDistrict;
    String[] cityList, districtList;
    OrderLineAdapter mOrderLineAdapter;
    LinearLayoutManager mLlManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        initView();
        setUpClick();
        addText();
    }

    private void setUpClick() {
        mBtnGoBack.setOnClickListener(this);
        mImgButtonBack.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonGoBack:
            case R.id.imageButtonBack:
                CheckOutActivity.this.finish();
                break;
            case R.id.buttonNext:
                String name = mTxtInputEdtTextName.getText().toString();
                String email = mTxtInputEdtTextEmail.getText().toString();
                String phone = mTxtInputEdtTextPhone.getText().toString();
                String address = mTxtInputEdtTextAddress.getText().toString() ;
                if (checkInformationEmpty(name, email, phone, address)) {
                       Intent intentConfirmOrder = new Intent(CheckOutActivity.this, ConfirmOrderActivity.class);
                       intentConfirmOrder.putExtra("name", name);
                       intentConfirmOrder.putExtra("email", email);
                       intentConfirmOrder.putExtra("phone", phone);
                       intentConfirmOrder.putExtra("address", address);
                       intentConfirmOrder.putExtra("city", cityList[mSpinnerChooseCity.getSelectedItemPosition()]);
                       intentConfirmOrder.putExtra("district", districtList[mSpinnerChooseDistrict.getSelectedItemPosition()]);
                       startActivity(intentConfirmOrder);
                }
                break;

        }
    }

    private boolean checkInformationEmpty(String fullName, String email, String phoneNumber, String address) {
        if (fullName.equals("")) {
            Toast.makeText(CheckOutActivity.this, "Vui lòng nhập Họ Và Tên", Toast.LENGTH_SHORT).show();
            return false;
        } else if (email.equals("")) {
            Toast.makeText(CheckOutActivity.this, "Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (phoneNumber.equals("")) {
            Toast.makeText(CheckOutActivity.this, "Vui lòng nhập Mật Khẩu", Toast.LENGTH_SHORT).show();
            return false;
        } else if (address.equals("")) {
            Toast.makeText(CheckOutActivity.this, "Vui lòng nhập Địa Chỉ", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void addText() {
        mTxtInputLayoutName.setHint(CreateHtmlTextHelper.createTextRequired("Họ tên"));
        mTxtInputLayoutEmail.setHint(CreateHtmlTextHelper.createTextRequired("Email"));
        mTxtInputLayoutPhone.setHint(CreateHtmlTextHelper.createTextRequired("Điện thoại"));
        mTxtInputLayoutAddress.setHint(CreateHtmlTextHelper.createTextRequired("Địa chỉ (Số nhà - Tên Đường - Phường/Xã"));

        mTxtViewShippingDateTitle.setText(CreateHtmlTextHelper.createTextRequired("Ngày giao hàng"));
        mTxtViewShippingTimeTitle.setText(CreateHtmlTextHelper.createTextRequired("Thời gian"));

        Calendar calendar = Calendar.getInstance();
        mTxtViewCurrentTime.setText(("Hôm nay " + calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + " (" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ")"));
        mTxtViewShippingDate.setText(("Hôm nay " + calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR)));
        mTxtViewShippingTime.setText(("Càng sớm càng tốt " + " (" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ")"));

        //Set up spinner City
        cityList = this.getResources().getStringArray(R.array.city);
        ArrayAdapter<String> dataAdapterCity = new ArrayAdapter<>(this, R.layout.layout_selected_city,
                R.id.textViewCity, cityList);
        dataAdapterCity.setDropDownViewResource(R.layout.layout_spinner_single_line);
        mSpinnerChooseCity.setAdapter(dataAdapterCity);
        mSpinnerChooseCity.setSelection(1);
        mSpinnerChooseCity.setOnItemSelectedListener(this);

        // Set up spinner District
        districtList = CreateDistrictListHelper.createDistrictList(CheckOutActivity.this, mSpinnerChooseCity.getSelectedItemPosition());
        ArrayAdapter<String> dataAdapterDistrict = new ArrayAdapter<String>(CheckOutActivity.this, R.layout.layout_selected_city,
                R.id.textViewCity, districtList);
        dataAdapterDistrict.setDropDownViewResource(R.layout.layout_spinner_single_line);
        mSpinnerChooseDistrict.setAdapter(dataAdapterDistrict);

        stringTotal = cart.getCartTotal();
        intTotal = CommonMethodHolder.convertStringToInt(stringTotal);
        if(intTotal > 150000){
            mTxtViewShippingFee.setText("Miễn phí");
            mTxtViewGrandTotalBlack.setText(stringTotal);
            mTxtViewGrandTotalRed.setText(stringTotal);
            mTxtViewOrderTotal.setText(stringTotal);
        }else{
            mTxtViewShippingFee.setText("10.000đ");
            mTxtViewOrderTotal.setText(stringTotal);
            intTotal = intTotal + 10000;
            stringTotal = CommonMethodHolder.convertIntToString(intTotal);
            mTxtViewGrandTotalBlack.setText(stringTotal);
            mTxtViewGrandTotalRed.setText(stringTotal);
        }
    }

    private void initView() {
        mTxtViewCurrentTime = findViewById(R.id.textViewCurrentTime);
        mTxtViewShippingDateTitle = findViewById(R.id.textViewShippingDateTitle);
        mTxtViewShippingDate = findViewById(R.id.textViewShippingDate);
        mTxtViewShippingTimeTitle = findViewById(R.id.textViewShippingTimeTitle);
        mTxtViewShippingTime = findViewById(R.id.textViewShippingTime);
        mTxtViewOrderTotal = findViewById(R.id.textViewOrderTotal);
        mTxtViewShippingFee = findViewById(R.id.textViewShippingFee);
        mTxtViewGrandTotalRed = findViewById(R.id.textViewGrandTotalRed);
        mTxtViewGrandTotalBlack = findViewById(R.id.textViewGrandTotalBlack);

        mTxtInputLayoutName = findViewById(R.id.textInputLayoutName);
        mTxtInputLayoutPhone = findViewById(R.id.textInputLayoutPhone);
        mTxtInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        mTxtInputLayoutAddress = findViewById(R.id.textInputLayoutAddress);

        mTxtInputEdtTextName = findViewById(R.id.editTextName);
        mTxtInputEdtTextPhone = findViewById(R.id.edittextPhone);
        mTxtInputEdtTextEmail = findViewById(R.id.editTextEmail);
        mTxtInputEdtTextAddress = findViewById(R.id.editTextAddress);

        mBtnGoBack = findViewById(R.id.buttonGoBack);
        mBtnNext = findViewById(R.id.buttonNext);

        mImgButtonBack = findViewById(R.id.imageButtonBack);

        mSpinnerChooseCity = findViewById(R.id.spinnerCity);
        mSpinnerChooseDistrict = findViewById(R.id.spinnerDistrict);

        mRcvOrderSummary = findViewById(R.id.recyclerViewOrderSummary);
        mLlManager = new LinearLayoutManager(CheckOutActivity.this, LinearLayoutManager.VERTICAL, false);
        mOrderLineAdapter = new OrderLineAdapter(cart.getArrListProductInCart(), CheckOutActivity.this);
        mRcvOrderSummary.setLayoutManager(mLlManager);
        mRcvOrderSummary.setAdapter(mOrderLineAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            mSpinnerChooseDistrict.setEnabled(false);
            mSpinnerChooseDistrict.setSelection(0);
        } else {
            mSpinnerChooseDistrict.setEnabled(true);
            districtList = CreateDistrictListHelper.createDistrictList(CheckOutActivity.this, position);
            ArrayAdapter<String> dataAdapterDistrict = new ArrayAdapter<String>(CheckOutActivity.this, R.layout.layout_selected_city,
                    R.id.textViewCity, districtList);
            dataAdapterDistrict.setDropDownViewResource(R.layout.layout_spinner_single_line);
            mSpinnerChooseDistrict.setAdapter(dataAdapterDistrict);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}