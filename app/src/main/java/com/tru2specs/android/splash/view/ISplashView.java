package com.tru2specs.android.splash.view;

/**
 * Created by GP00471911 on 21-06-2017.
 */

public interface ISplashView {
    void navigateToScreen();
    void navigateToDashboard();
    void navigateToBasicInfoScreen();
    void onFailure(String message);
}
