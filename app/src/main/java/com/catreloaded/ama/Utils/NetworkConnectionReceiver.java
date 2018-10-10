package com.catreloaded.ama.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.catreloaded.ama.MainActivity;
import com.catreloaded.ama.R;

public class NetworkConnectionReceiver extends BroadcastReceiver {

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")){
            if(!isOnline()){
                try {
                    MainActivity.showSnackbar(context.getString(R.string.no_internet_connection));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                try {
                    MainActivity.showSnackbar(context.getString(R.string.connected));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This method checks the internet state of the device
     * @return true if there is an active connection
     */
    private boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
