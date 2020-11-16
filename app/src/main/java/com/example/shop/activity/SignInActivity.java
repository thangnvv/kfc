package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.ultil.CreateHtmlText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    TextInputEditText mEdtTextEmail, mEdtTextPassword;
    TextInputLayout mTxtInputLayoutEmail, mTxtInputLayoutPassword;
    Button mBtnSignInWithMail;
    TextView mTxtViewSignUpNow,mTxtViewSignInWithFaceBook, mTxtViewSignInWithGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initView();

        mTxtInputLayoutEmail.setHint(CreateHtmlText.createTextRequired("Email"));
        mTxtInputLayoutPassword.setHint(CreateHtmlText.createTextRequired("Mật khẩu"));

        mBtnSignInWithMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEdtTextEmail.getText().toString();
                String password = mEdtTextPassword.getText().toString();
                if(!email.equals("") && !password.equals("")){
                    final FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        finish();
                                    } else {
                                        Log.d("EEE", "In Sign In Activity: " + task.getException());
                                        Toast.makeText(SignInActivity.this, "Sai thông tin!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(SignInActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    private void initView() {
        mEdtTextEmail = findViewById(R.id.editTextEmail);
        mEdtTextPassword = findViewById(R.id.editTextPassword);
        mBtnSignInWithMail = findViewById(R.id.buttonSignInWithMail);
        mTxtViewSignUpNow = findViewById(R.id.textViewSignUpNow);
        mTxtViewSignInWithFaceBook = findViewById(R.id.textViewSignInViaFacebook);
        mTxtViewSignInWithGoogle = findViewById(R.id.textViewSignInViaGoogle);
        mTxtInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        mTxtInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
    }
}