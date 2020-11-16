package com.example.shop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.ultil.CreateHtmlText;
import com.example.shop.ultil.CustomDialogPolicyAndRegulation;
import com.example.shop.ultil.CustomDialogTermsAndConditons;
import com.example.shop.ultil.Customer;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;


import java.util.Arrays;


public class AccountRegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    TextInputEditText mTxtInputName, mTxtInputEmail, mTxtInputPassword, mTxtInputRepeatPW, mTxtInputPhone;
    TextInputLayout mTxtInputLayoutName, mTxtInputLayoutEmail, mTxtInputLayoutPassword, mTxtInputLayoutRepeatPW, mTxtInputLayoutPhone;
    TextView mTxtViewNotice, mTxtViewAcceptPolicy, mTxtViewAcceptReceiptPromo, mTxtViewAcceptSignUp, mTxtViewSignIn;
    Button mBtnRegisterWithEmail;
    CheckBox mCbAcceptPolicy;
    LoginButton mSignInFacebookButton;
    CallbackManager mCallBackManager;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton mSignInGoogleButton;
    int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);

        initView();
        addText();
        setUpViewOnClick();

        String acceptSignUp = getString(R.string.acceptSignUp);
        setClickAbleAcceptSignUp(acceptSignUp, acceptSignUp.indexOf("Điều"),
                acceptSignUp.indexOf("và"), acceptSignUp.indexOf("Chính"), acceptSignUp.length());

        // Facebook
        mSignInFacebookButton.setPermissions(Arrays.asList("email", "public_profile"));
        mSignInFacebookButton.registerCallback(mCallBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                Log.d("DDD", "At Account Register Activity in button Click succress");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("DDD", "At Account Register Activity in button Click error: " + error.getMessage());
            }
        });

        // Google
        mSignInGoogleButton.setSize(SignInButton.SIZE_WIDE);
        setGoogleButtonText(mSignInGoogleButton, "Đăng ký với Google");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void setUpViewOnClick() {
        mTxtViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mSignInGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        mBtnRegisterWithEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullName = mTxtInputName.getText().toString();
                final String email = mTxtInputEmail.getText().toString();
                final String password = mTxtInputPassword.getText().toString();
                String repeatPassword = mTxtInputRepeatPW.getText().toString();
                final String phoneNumber = mTxtInputPhone.getText().toString();
                boolean isAcceptPolicy = mCbAcceptPolicy.isChecked();
                if (checkInformationEmpty(fullName, email, password, repeatPassword, phoneNumber, isAcceptPolicy)) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(AccountRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Customer customer = new Customer(user.getUid(), fullName, email, password, phoneNumber);
                                        DatabaseReference newRef = mDatabase.child("user").child(user.getUid());
                                        newRef.setValue(customer);
                                        finish();
                                    } else {
                                        Log.d("EEE", "In Account Register Activity: " + task.getException());
                                    }
                                }
                            });
                } else {
                    Toast.makeText(AccountRegisterActivity.this, "Register Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("DDD", "At Account Register Activity In Handle Facebook Access");
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Customer customer = new Customer(user.getUid(), user.getDisplayName(), user.getEmail(), "", user.getPhoneNumber());
                            DatabaseReference newRef = mDatabase.child("user").child(user.getUid());
                            newRef.setValue(customer);
                            Intent intentGoToMain = new Intent(AccountRegisterActivity.this, MainActivity.class);
                            intentGoToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(new Intent(AccountRegisterActivity.this, MainActivity.class));
                            Log.d("DDD", "At Account Register Activity In Handle Facebook Access Success");
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.

                        }

                        // ...
                    }
                });
    }


    private void setClickAbleAcceptSignUp(String str, int startOperation, int endOperation, int startPolicy, int endPolicy) {
        SpannableString spn = new SpannableString(str);
        ClickableSpan clkspan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                CustomDialogTermsAndConditons dialogTermsAndConditions = new CustomDialogTermsAndConditons(AccountRegisterActivity.this);
                dialogTermsAndConditions.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogTermsAndConditions.show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.red));
            }
        };
        ClickableSpan clkspan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                CustomDialogPolicyAndRegulation dialogPolicyAndRegulation = new CustomDialogPolicyAndRegulation(AccountRegisterActivity.this);
                dialogPolicyAndRegulation.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogPolicyAndRegulation.show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.red));
            }
        };
        spn.setSpan(clkspan, startOperation, endOperation, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spn.setSpan(clkspan1, startPolicy, endPolicy, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTxtViewAcceptSignUp.setText(spn);
        mTxtViewAcceptSignUp.setMovementMethod(LinkMovementMethod.getInstance());
        mTxtViewAcceptSignUp.setHighlightColor(Color.TRANSPARENT);
    }

    private void addText() {
        mTxtInputLayoutName.setHint(CreateHtmlText.createTextRequired("Họ tên"));
        mTxtInputLayoutEmail.setHint(CreateHtmlText.createTextRequired("Email"));
        mTxtInputLayoutPassword.setHint(CreateHtmlText.createTextRequired("Mật khẩu"));
        mTxtInputLayoutRepeatPW.setHint(CreateHtmlText.createTextRequired("Lặp lại mật khẩu"));
        mTxtInputLayoutPhone.setHint(CreateHtmlText.createTextRequired("Điện thoại"));
        mTxtViewNotice.setText(CreateHtmlText.buildText(getString(R.string.registerNotice)));
        mTxtViewAcceptPolicy.setText(CreateHtmlText.buildText(getString(R.string.acceptPolicy)));
        mTxtViewAcceptReceiptPromo.setText(CreateHtmlText.buildText(getString(R.string.acceptReceiptPromo)));
    }

    private void initView() {
        mTxtInputName = findViewById(R.id.edittextName);
        mTxtInputEmail = findViewById(R.id.edittextEmail);
        mTxtInputPassword = findViewById(R.id.edittextPassword);
        mTxtInputRepeatPW = findViewById(R.id.edittextRepeat);
        mTxtInputPhone = findViewById(R.id.edittextPhone);

        mTxtInputLayoutName = findViewById(R.id.textInputLayoutName);
        mTxtInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        mTxtInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        mTxtInputLayoutRepeatPW = findViewById(R.id.textInputLayoutRepeat);
        mTxtInputLayoutPhone = findViewById(R.id.textInputLayoutPhone);

        mBtnRegisterWithEmail = findViewById(R.id.buttonRegister);
        mSignInFacebookButton = findViewById(R.id.signInFacebookButton);
        mSignInGoogleButton = findViewById(R.id.signInGoogleButton);

        mCbAcceptPolicy = findViewById(R.id.checkBoxAcceptPolicy);
        mTxtViewNotice = findViewById(R.id.textViewNotice);
        mTxtViewAcceptPolicy = findViewById(R.id.textViewAcceptPolicy);
        mTxtViewAcceptReceiptPromo = findViewById(R.id.textViewAcceptReceiptPromo);
        mTxtViewAcceptSignUp = findViewById(R.id.textViewAcceptSignUp);
        mTxtViewSignIn = findViewById(R.id.textViewSignIn);


        mCallBackManager = CallbackManager.Factory.create();
    }

    private boolean checkInformationEmpty(String fullName, String email, String password, String repeatPassword, String phoneNumber, boolean isAcceptPolicy) {
        if (fullName.equals("")) {
            Toast.makeText(AccountRegisterActivity.this, "Vui lòng nhập Họ Và Tên", Toast.LENGTH_SHORT).show();
            return false;
        } else if (email.equals("")) {
            Toast.makeText(AccountRegisterActivity.this, "Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.equals("")) {
            Toast.makeText(AccountRegisterActivity.this, "Vui lòng nhập Mật Khẩu", Toast.LENGTH_SHORT).show();
            return false;
        } else if (repeatPassword.equals("") || !repeatPassword.equals(password)) {
            Toast.makeText(AccountRegisterActivity.this, "Mật khẩu và mật khẩu lặp lại không trùng khớp", Toast.LENGTH_SHORT).show();
            return false;
        } else if (phoneNumber.equals("")) {
            Toast.makeText(AccountRegisterActivity.this, "Vui lòng nhập Số điện thoại", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!isAcceptPolicy) {
            Toast.makeText(AccountRegisterActivity.this, "Vui lòng đọc và đồng ý với chính sách và điều khoản của KFC Việt Nam", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            mCallBackManager.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
        }



    }
    // Facebook
//    AccessToken accessToken = AccessToken.getCurrentAccessToken();
//    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//
//    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
//        @Override
//        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//            if(currentAccessToken == null){
//                LoginManager.getInstance().logOut();
//                Log.d("DDD", "Log out");
//            }
//        }
//    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        accessTokenTracker.stopTracking();
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);
    }

    // Google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            updateUI(null);
        }
    }

    protected void setGoogleButtonText(SignInButton signInButton, String buttonText) {
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setText(buttonText);
                tv.setTextSize(getResources().getDimension(R.dimen._5sdp));
                tv.setTextColor(getResources().getColor(R.color.darkGrey));
                return;
            }
        }
    }
}