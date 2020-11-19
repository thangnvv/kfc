package com.example.shop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.adapter.OrderLineAdapter;
import com.example.shop.utils.objects.Cart;
import com.example.shop.utils.CommonMethodHolder;
import com.example.shop.utils.objects.Order;
import com.example.shop.utils.objects.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ConfirmOrderActivity extends AppCompatActivity {
    Cart cart = Hawk.get("cart");
    int intTotal;
    String stringTotal;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final FirebaseUser mCurrentUser = mAuth.getCurrentUser();
    private final DatabaseReference mDataRef = FirebaseDatabase.getInstance().getReference();
    ImageButton mImgButtonBack;
    TextView mTxtViewName, mTxtViewAddress, mTxtViewPhone, mTxtViewCity, mTxtViewDistrict, mTxtViewEmail,
            mTxtViewOrderTotal, mTxtViewShippingFee, mTxtViewGrandTotalRed, mTxtViewGrandTotalBlack;
    Button mBtnBack, mBtnConfirmOrder;
    RecyclerView mRcvOrderSummary;
    OrderLineAdapter mOrderLineAdapter;
    LinearLayoutManager mLlManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        initView();
        addText();

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mImgButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentUser != null) {
                    Order order = new Order();
                    order.setTotal(stringTotal);
                    order.setListProduct(cart.getArrListProductInCart());
                    order.setDate(new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime()));
                    order.setShippingFee(mTxtViewShippingFee.getText().toString());
                    Gson gson = new Gson();
                    String orderJson = gson.toJson(order);
//                    DatabaseReference mDataRef = FirebaseDatabase.getInstance().getReference();
//                    FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                    mDataRef.child("user").child(mCurrentUser.getUid()).child("order_history").push().setValue(orderJson);
                }

                cart.setCartCount(0);
                cart.setCartTotal("");
                cart.setArrListProductInCart(new ArrayList<Product>());
                cart.setRequireFromEditProduct(false);
                Hawk.put("orderSuccessful", true);
                CommonMethodHolder.saveCart(cart.getArrListProductInCart(), cart.getCartCount(), cart.getCartTotal(), false, cart);
                Intent intentMain = new Intent(ConfirmOrderActivity.this, MainActivity.class);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentMain);
                Toast.makeText(ConfirmOrderActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addText() {
        Intent intentData = getIntent();
        mTxtViewName.setText(intentData.getStringExtra("name"));
        mTxtViewAddress.setText(intentData.getStringExtra("address"));
        mTxtViewPhone.setText(intentData.getStringExtra("phone"));
        mTxtViewCity.setText(intentData.getStringExtra("city"));
        mTxtViewDistrict.setText(intentData.getStringExtra("district"));
        mTxtViewEmail.setText(intentData.getStringExtra("email"));


        stringTotal = cart.getCartTotal();
        intTotal = CommonMethodHolder.convertStringToInt(stringTotal);
        if (intTotal > 150000) {
            mTxtViewShippingFee.setText("Miễn phí");
            mTxtViewGrandTotalBlack.setText(stringTotal);
            mTxtViewGrandTotalRed.setText(stringTotal);
            mTxtViewOrderTotal.setText(stringTotal);
        } else {
            mTxtViewShippingFee.setText("10.000đ");
            mTxtViewOrderTotal.setText(stringTotal);
            intTotal = intTotal + 10000;
            stringTotal = CommonMethodHolder.convertIntToString(intTotal);
            mTxtViewGrandTotalBlack.setText(stringTotal);
            mTxtViewGrandTotalRed.setText(stringTotal);
        }

    }

    private void initView() {
        mRcvOrderSummary = findViewById(R.id.recyclerViewOrderSummary);
        mLlManager = new LinearLayoutManager(ConfirmOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        mOrderLineAdapter = new OrderLineAdapter(cart.getArrListProductInCart(), ConfirmOrderActivity.this);
        mRcvOrderSummary.setLayoutManager(mLlManager);
        mRcvOrderSummary.setAdapter(mOrderLineAdapter);

        mTxtViewName = findViewById(R.id.textViewName);
        mTxtViewAddress = findViewById(R.id.textViewAddress);
        mTxtViewPhone = findViewById(R.id.textViewPhone);
        mTxtViewCity = findViewById(R.id.textViewChosenCity);
        mTxtViewDistrict = findViewById(R.id.textViewChosenDistrict);
        mTxtViewEmail = findViewById(R.id.textViewEmail);
        mTxtViewOrderTotal = findViewById(R.id.textViewOrderTotal);
        mTxtViewShippingFee = findViewById(R.id.textViewShippingFee);
        mTxtViewGrandTotalRed = findViewById(R.id.textViewGrandTotalRed);
        mTxtViewGrandTotalBlack = findViewById(R.id.textViewGrandTotalBlack);

        mBtnBack = findViewById(R.id.buttonGoBack);
        mBtnConfirmOrder = findViewById(R.id.buttonConfirmOrder);
        mImgButtonBack = findViewById(R.id.imageButtonBack);
    }
}