package com.tru2specs.android;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.tru2specs.android.objects.request.AddressRequest;
import com.tru2specs.android.receiver.ConnectivityReceiver;

import java.util.ArrayList;

/**
 * Created by GP00471911 on 31-01-2017.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;
    private ArrayList<AddressRequest> userAddress = new ArrayList<AddressRequest>();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        Iconify.with(new FontAwesomeModule());
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    // Temporarily saved user entered address for checkout
    public void saveUserAddress(AddressRequest address) {
        userAddress.add(address);
    }

    public ArrayList<AddressRequest> getUserAddressList() {
       return userAddress;
    }

}
