package com.tru2specs.android.login.presenter;

import android.os.Build;
import android.text.TextUtils;

import com.tru2specs.android.login.listerner.OnLoginListener;
import com.tru2specs.android.login.model.LoginInteractor;
import com.tru2specs.android.login.view.ILoginView;
import com.tru2specs.android.objects.request.LoginRequest;
import com.tru2specs.android.objects.responses.loginSignup.Data;
/**
 * Created by ajaykumarsantra on 03/07/17.
 */

public class LoginPresenter implements ILoginPresenter, OnLoginListener {


    private final ILoginView mView;
    private final LoginInteractor mInteractor;
    private String DEVICE_TYPE_ANDROID = "ANDROID: ";


    public LoginPresenter(ILoginView view) {
        this.mView = view;
        this.mInteractor = new LoginInteractor();
    }

    @Override
    public boolean validateFields(LoginRequest loginRequest) {

        if (TextUtils.isEmpty(loginRequest.getEmail())) {
            mView.onLoginFailed("Please enter email.");
            return false;
        }

        if (TextUtils.isEmpty(loginRequest.getPassword())) {
            mView.onLoginFailed("Please enter password.");
            return false;
        }

        return true;
    }

    @Override
    public void attempToLogin() {
        mView.showProgressView();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(mView.getEmail());
        loginRequest.setPassword(mView.getPassword());
        loginRequest.setDiveceDetails(DEVICE_TYPE_ANDROID + Build.MANUFACTURER + ", " + Build.MODEL + ", " + Build.DISPLAY);

        if (validateFields(loginRequest)) {
            mInteractor.submitLogin(this, loginRequest);
            return;
        }
        mView.hideProgressView();

    }

    @Override
    public void onLoginSuccess(Data data) {
        mView.hideProgressView();
//new SessionManager()
        mView.onLoginSuccess("Login Successful!", data);

    }

    @Override
    public void onLoginFailure(String message) {
        mView.hideProgressView();
        mView.onLoginFailed(message);
    }

}
