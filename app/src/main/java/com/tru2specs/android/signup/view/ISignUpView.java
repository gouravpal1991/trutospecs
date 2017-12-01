package com.tru2specs.android.signup.view;

import com.tru2specs.android.objects.responses.loginSignup.Data;
/**
 * Created by GP00471911 on 19-06-2017.
 */

public interface ISignUpView {
    void setScreenTitle();
    void signUpFailed(String message);
    void signUpSuccess(String message, Data data);
    String getFullName();
    String getEmail();
    String getPassword();
    String getContactNo();
    String getGender();
    String getReferralCode();
    void setBasicInfo();
    boolean getTermsAndConditionCheckStatus();
    void hideProgressView();
    void showProgressView();
}
