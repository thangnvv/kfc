package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.utils.CreateHtmlTextHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePasswordActivity extends AppCompatActivity {
    TextInputLayout mTxtInputLayoutCurrentPW, mTxtInputLayoutNewPW, mTxtInputLayoutRepeatNewPW;
    TextInputEditText mTxtInputEdtTextCurrentPW, mTxtInputEdtTextNewPW, mTxtInputEdtTextRepeatNewPW;
    ImageButton mImgButtonClose;
    Button mBtnUpdatePassword;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser mCurrentUser = mAuth.getCurrentUser();
    private DatabaseReference mDataRef = FirebaseDatabase.getInstance().getReference();
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
        addText();

        mDataRef.child("user").child(mCurrentUser.getUid()).child("password").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                password = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        mImgButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentPassword = mTxtInputEdtTextCurrentPW.getText().toString();
                String newPassword = mTxtInputEdtTextNewPW.getText().toString();
                String repeatNewPassword = mTxtInputEdtTextRepeatNewPW.getText().toString();
                if(checkInformationEmpty(currentPassword, newPassword, repeatNewPassword)){
                       if(password.equals(currentPassword)){
                           password = newPassword;
                           mCurrentUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        mDataRef.child("user").child(mCurrentUser.getUid()).child("password").setValue(password);
                                        Toast.makeText(ChangePasswordActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                        Log.d("EEE", "In Change Password Activity: " + task.getException());
                                    }
                               }
                           });
                       }
                }
            }
        });
    }

    private boolean checkInformationEmpty(String currentPassword, String newPassword, String repeatNewPassword) {
        if (currentPassword.equals("")) {
            Toast.makeText(ChangePasswordActivity.this, "Vui lòng nhập Mật Khẩu Cũ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (newPassword.equals("")) {
            Toast.makeText(ChangePasswordActivity.this, "Vui lòng nhập Mật Khẩu Mới", Toast.LENGTH_SHORT).show();
            return false;
        } else if (repeatNewPassword.equals("")) {
            Toast.makeText(ChangePasswordActivity.this, "Vui lòng nhập lại Mật Khẩu Mới", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!newPassword.equals(repeatNewPassword)) {
            Toast.makeText(ChangePasswordActivity.this, "Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    private void addText() {
        mTxtInputLayoutCurrentPW.setHint(CreateHtmlTextHelper.createTextRequired(getString(R.string.hint_current_password)));
        mTxtInputLayoutNewPW.setHint(CreateHtmlTextHelper.createTextRequired(getString(R.string.hint_new_password)));
        mTxtInputLayoutRepeatNewPW.setHint(CreateHtmlTextHelper.createTextRequired(getString(R.string.hint_confirm_password)));
    }

    private void initView() {
        mTxtInputLayoutCurrentPW = findViewById(R.id.textInputLayoutCurrentPW);
        mTxtInputLayoutNewPW = findViewById(R.id.textInputLayoutNewPW);
        mTxtInputLayoutRepeatNewPW = findViewById(R.id.textInputLayoutRepeatNewPW);

        mTxtInputEdtTextCurrentPW = findViewById(R.id.textInputEditTextCurrentPW);
        mTxtInputEdtTextNewPW = findViewById(R.id.textInputEditTextNewPW);
        mTxtInputEdtTextRepeatNewPW = findViewById(R.id.textInputEditTextRepeatNewPW);

        mImgButtonClose = findViewById(R.id.imageButtonClose);
        mBtnUpdatePassword = findViewById(R.id.buttonUpdatePassword);

    }
}