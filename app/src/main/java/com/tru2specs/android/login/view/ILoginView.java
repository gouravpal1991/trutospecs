package com.tru2specs.android.login.view;

import com.tru2specs.android.objects.responses.loginSignup.Data;
/**
 * Created by gouravpal on 02/07/17.
 */

public interface ILoginView
{
    void setScreenTitle();
    String getEmail();
    String getPassword();
    void navigateToForgotPassword();
    void navigateToSignUp();
    void googleSignIn();
    void googleSignOut();
    void facebookSignIn();
    void showProgressView();
    void hideProgressView();
    void onLoginSuccess(String message, Data data);
    void onLoginFailed(String message);
}
