package com.tru2specs.android.base;

import android.support.v7.app.AppCompatActivity;

import com.tru2specs.android.receiver.ConnectivityReceiver;

/**
 * Created by ajaykumarsantra on 14/07/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        onNetworkChange(isConnected);
    }

    // Showing the status in Snackbar
    public abstract void onNetworkChange(boolean isConnected);

//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }
}
