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
import android.widget.TextView;
import com.example.shop.R;
import com.example.shop.ultil.CreateHtmlText;
import com.example.shop.ultil.CustomDialogPolicyAndRegulation;
import com.example.shop.ultil.CustomDialogTermsAndConditons;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;

public class AccountRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout mTxtInputName, mTxtInputEmail, mTxtInputPassword, mTxtInputRepeatPW, mTxtInputPhone;
    TextView mTxtViewNotice, mTxtViewAcceptPolicy, mTxtViewAcceptReceiptPromo, mTxtViewAcceptSignUp, mTxtViewSignIn;
    LoginButton mSignInFacebookButton;
    CallbackManager mCallBackManager;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton mSignInGoogleButton;
    int RC_SIGN_IN = 123;
    String firstName = "";
    String lastName = "";

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

        mSignInFacebookButton.setPermissions(Arrays.asList("email", "public_profile"));
        mSignInFacebookButton.setText("Đăng nhập bằng Facebook");
        mSignInFacebookButton.registerCallback(mCallBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        // Application code
                        Log.d("DDD", object.toString());

                        try {
                            firstName = object.getString("first_name");
                            lastName = object.getString("last_name");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString(firstName, lastName);
                request.setParameters(bundle);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        mSignInGoogleButton.setSize(SignInButton.SIZE_WIDE);
        setGoogleButtonText(mSignInGoogleButton, "Đăng ký với Google");


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        updateUI(account);

    }

    private void setUpViewOnClick() {
        mTxtViewSignIn.setOnClickListener(this);
        mSignInGoogleButton.setOnClickListener(this);
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
        mTxtInputName.setHint(CreateHtmlText.createTextRequired("Họ tên"));
        mTxtInputEmail.setHint(CreateHtmlText.createTextRequired("Email"));
        mTxtInputPassword.setHint(CreateHtmlText.createTextRequired("Mật khẩu"));
        mTxtInputRepeatPW.setHint(CreateHtmlText.createTextRequired("Lặp lại mật khẩu"));
        mTxtInputPhone.setHint(CreateHtmlText.createTextRequired("Điện thoại"));
        mTxtViewNotice.setText(CreateHtmlText.buildText(getString(R.string.registerNotice)));
        mTxtViewAcceptPolicy.setText(CreateHtmlText.buildText(getString(R.string.acceptPolicy)));
        mTxtViewAcceptReceiptPromo.setText(CreateHtmlText.buildText(getString(R.string.acceptReceiptPromo)));
    }


    private void initView() {
        mTxtInputName = findViewById(R.id.textInputLayoutName);
        mTxtInputEmail = findViewById(R.id.textInputLayoutEmail);
        mTxtInputPassword= findViewById(R.id.textInputLayoutPassword);
        mTxtInputRepeatPW = findViewById(R.id.textInputLayoutRepeat);
        mTxtInputPhone = findViewById(R.id.textInputLayoutPhone);
        mTxtViewNotice = findViewById(R.id.textViewNotice);
        mTxtViewAcceptPolicy = findViewById(R.id.textViewAcceptPolicy);
        mTxtViewAcceptReceiptPromo = findViewById(R.id.textViewAcceptReceiptPromo);
        mTxtViewAcceptSignUp = findViewById(R.id.textViewAcceptSignUp);
        mTxtViewSignIn = findViewById(R.id.textViewSignIn);
        mSignInFacebookButton = findViewById(R.id.signInFacebookButton);
        mSignInGoogleButton = findViewById(R.id.signInGoogleButton);

        mCallBackManager = CallbackManager.Factory.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textViewSignIn:
                break;
            case R.id.signInGoogleButton:
                signIn();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }else{
            mCallBackManager.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken == null){
                LoginManager.getInstance().logOut();
                Log.d("DDD", "Log out");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
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