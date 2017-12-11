package com.tru2specs.android.profile.view;

import com.tru2specs.android.objects.responses.loginSignup.Data;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public interface IProfileView {
    void setScreenTitle();
    void updateUserFailed(String message);
    void updateUserSuccess(String message, Data data);
    String getFullName();
    String getEmail();
    String getPassword();
    String getContactNo();
    String getGender();
    String getReferralCode();
    String getDob();
    void setBasicInfo();
    void hideProgressView();
    void showProgressView();
    void navigateToLoginActivity();
}
