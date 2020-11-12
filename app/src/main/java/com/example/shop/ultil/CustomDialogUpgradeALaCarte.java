package com.example.shop.ultil;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.adapter.ALaCarteUpgradeAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomDialogUpgradeALaCarte extends Dialog implements View.OnClickListener, ALaCarteUpgradeAdapter.OnUpgradeChooseListener {
    TextView mTxtViewTitle;
    ImageButton mImgButtonCancel;
    Button mBtnConfirm;
    RecyclerView rvALaCarteUpgrade;
    ALaCarteUpgradeAdapter aLaCarteUpgradeAdapter;
    ArrayList<Upgrade> upgrades;
    int chosenPosition, priceChange;
    boolean upgradeChange = false, multiUpgrade = false;
    Context context;
    OnConfirmChosenUpgrade mOnConfirmChosenUpgrade;

    public void setOnConfirmChosenUpgrade(OnConfirmChosenUpgrade mOnConfirmChosenUpgrade){
        this.mOnConfirmChosenUpgrade = mOnConfirmChosenUpgrade;
    }

    public CustomDialogUpgradeALaCarte(@NonNull Context context, ArrayList<Upgrade> upgrades, int chosenPosition) {
        super(context);
        this.context = context;
        this.chosenPosition = chosenPosition;
        this.upgrades = upgrades;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_change_alacarte_upgrade);

        initView();
        setViewClick();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvALaCarteUpgrade.setLayoutManager(linearLayoutManager);
        rvALaCarteUpgrade.setAdapter(aLaCarteUpgradeAdapter);
        aLaCarteUpgradeAdapter.setOnUpgradeChooseListener(this);
        mTxtViewTitle.setText(("ĐỔI SIZE "+  upgrades.get(chosenPosition).getCategory()));
    }

    private void setViewClick() {
        mBtnConfirm.setOnClickListener(this);
        mImgButtonCancel.setOnClickListener(this);
    }

    private void initView() {
        mTxtViewTitle = findViewById(R.id.textViewTitle);
        mImgButtonCancel = findViewById(R.id.imageButtonCancel);
        mBtnConfirm     = findViewById(R.id.buttonConfirm);
        rvALaCarteUpgrade = findViewById(R.id.recyclerViewALaCarteUpgrade);
        aLaCarteUpgradeAdapter = new ALaCarteUpgradeAdapter(upgrades, context, upgrades.get(chosenPosition));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButtonCancel:
                dismiss();
                break;
            case R.id.buttonConfirm:
                if(multiUpgrade){
                    ArrayList<Upgrade> upgradeList = new ArrayList<>();
                    for (int i = 0; i < upgrades.size(); i++) {
                        if (upgrades.get(i).getPortion() > 0) {
                            upgradeList.add(upgrades.get(i));
                        }
                    }
                    mOnConfirmChosenUpgrade.confirmChooseMultipleUpgrade(upgradeList);
                }else{
                    if(upgradeChange){
                        mOnConfirmChosenUpgrade.confirmUpgrade(upgrades.get(chosenPosition), chosenPosition, priceChange);
                    }
                }
                dismiss();
                break;
        }
    }

    @Override
    public void onChoose(Upgrade upgrade, int position) {

        if(position!= chosenPosition){
            if(upgrade.getPrice_change().equals("")){
                priceChange = - CommonMethodHolder.convertStringToInt(upgrades.get(chosenPosition).getPrice_change());
            }else if(upgrades.get(chosenPosition).getPrice_change().equals("")){
                priceChange = CommonMethodHolder.convertStringToInt(upgrade.getPrice_change());
            }else{
                priceChange = CommonMethodHolder.convertStringToInt(upgrade.getPrice_change()) - CommonMethodHolder.convertStringToInt(upgrades.get(chosenPosition).getPrice_change());
            }

            aLaCarteUpgradeAdapter.notifyDataSetChanged();
            chosenPosition = position;
            upgradeChange = true;
            multiUpgrade = false;
        }else{
            upgradeChange = false;
        }

    }

    @Override
    public void onUpgradeChangePortion() {
        aLaCarteUpgradeAdapter.notifyDataSetChanged();
        multiUpgrade = true;
    }

    public interface OnConfirmChosenUpgrade {
        void confirmUpgrade(Upgrade chosenUpgrade, int positionUpgrade, int priceChange);
        void confirmChooseMultipleUpgrade(ArrayList<Upgrade> upgradeList);
    }

}
