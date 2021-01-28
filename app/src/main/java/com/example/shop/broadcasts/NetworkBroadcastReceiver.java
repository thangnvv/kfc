package com.example.shop.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn =  (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            onNetworkChangeListener.onNetworkChange(true);
        }else{
            onNetworkChangeListener.onNetworkChange(false);
        }
    }

    private OnNetworkChangeListener onNetworkChangeListener;

    public void setOnNetworkChangeListener(OnNetworkChangeListener onNetworkChangeListener) {
         this.onNetworkChangeListener = onNetworkChangeListener;
    }

    public interface OnNetworkChangeListener{
        void onNetworkChange(boolean hasNetwork);
    }
}
