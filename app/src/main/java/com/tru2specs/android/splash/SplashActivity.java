package com.tru2specs.android.splash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tru2specs.android.MyApplication;
import com.tru2specs.android.R;
import com.tru2specs.android.base.BaseActivity;
import com.tru2specs.android.basicinfo.BasicInfoActivity;
import com.tru2specs.android.dashboard.DashboardActivity;
import com.tru2specs.android.manager.SessionManager;
import com.tru2specs.android.receiver.ConnectivityReceiver;
import com.tru2specs.android.splash.view.ISplashView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GP00471911 on 21-06-2017.
 */

public class SplashActivity extends BaseActivity implements ISplashView {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @BindView(R.id.content_main)
    RelativeLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);
        checkConnection();
    }

    @Override
    public void navigateToScreen() {
        if (new SessionManager(getApplicationContext()).isBasicInfoStored()) {
            navigateToDashboard();
            return;
        }
        navigateToBasicInfoScreen();
    }

    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        onNetworkChange(isConnected);
    }

    @Override
    public void navigateToDashboard() {
        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToBasicInfoScreen() {
        Intent intent = new Intent(SplashActivity.this, BasicInfoActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }


    @Override
    public void onNetworkChange(boolean isConnected) {
        String message = "";
        int color = Color.WHITE;
        if (isConnected) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    navigateToScreen();

                }
            }, SPLASH_TIME_OUT);

            return;
        }
        message = "Sorry! Not connected to internet";
        Snackbar snackbar = Snackbar
                .make(mRoot, message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }


}
