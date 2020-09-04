package com.example.shop.ultil;

import android.os.CountDownTimer;
import android.util.Log;

import com.example.shop.activity.MainActivity;

public class TimerForSildeShow {
    private int mCurrentPosition = 0;
    private boolean isSlided = false;

//    public void startAutoSlideTimer(){
//        CountDownTimer timerCountDown = new CountDownTimer(16000, 4000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                Log.d("Check", millisUntilFinished + " " );
//                if(mCurrentPosition >=4){
//                    Log.d("Check", millisUntilFinished + "- on reload position -" + mCurrentPosition );
//                    mCurrentPosition =0;
//                }
//                MainActivity.getInstance().PrepareDots(mCurrentPosition++);
//            }
//
//            @Override
//            public void onFinish() {
//                this.start();
//            }
//        };
//        timerCountDown.start();
//    }

    public void stopAutoSlideTimer(){

    }
}
