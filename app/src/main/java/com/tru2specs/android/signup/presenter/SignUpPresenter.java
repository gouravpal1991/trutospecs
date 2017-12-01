package com.tru2specs.android.signup.presenter;

import android.text.TextUtils;

import com.tru2specs.android.objects.request.SignUpRequest;
import com.tru2specs.android.objects.responses.loginSignup.Data;
import com.tru2specs.android.signup.listener.OnSignUpFinishedListener;
import com.tru2specs.android.signup.model.SignUpInteractor;
import com.tru2specs.android.signup.view.ISignUpView;
import com.tru2specs.android.util.Constants;
import com.tru2specs.android.util.Helper;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public class SignUpPresenter implements ISignUpPresenter, OnSignUpFinishedListener {

    private final ISignUpView mView;
    private final SignUpInteractor mInteractor;

    public SignUpPresenter(ISignUpView view) {
        this.mView = view;
        this.mInteractor = new SignUpInteractor();
    }

    @Override
    public boolean validateFields(SignUpRequest signUpRequest) {
        if (TextUtils.isEmpty(signUpRequest.getFirstName())) {
            mView.signUpFailed("Please enter full name.");
            return false;
        }
        if (TextUtils.isEmpty(signUpRequest.getEmail()) || !android.util.Patterns.EMAIL_ADDRESS.matcher(signUpRequest.getEmail()).matches()) {
            mView.signUpFailed("Please enter valid email id.");
            return false;
        }

        //region Password Validation
        String pwd = signUpRequest.getPassword();
        if (!Helper.validatePassword(pwd)) {
            mView.signUpFailed("Password must contains one digit from 0-9.\n" +
                    "It must contain one lowercase character.\n" +
                    "It must contain one uppercase character.\n" +
                    "It must contain one special symbols in the list \"@#$%\".\n" +
                    "It should be at least 6 characters and maximum of 20.");
            return false;
        }
        //endregion

        if (TextUtils.isEmpty(signUpRequest.getContactNo())) {
            mView.signUpFailed("Please enter contact number.");
            return false;
        }
        if (TextUtils.isEmpty(signUpRequest.getGender())) {
            mView.signUpFailed("Please select gender.");
            return false;
        }

        if (!mView.getTermsAndConditionCheckStatus()) {
            mView.signUpFailed("Please accept terms and condition.");
            return false;
        }
        return true;
    }

    @Override
    public void attemptSignUpFormSubmission() {
        mView.showProgressView();

        SignUpRequest request = new SignUpRequest();
        request.setFirstName(mView.getFullName());
        request.setPassword(mView.getPassword());
        request.setEmail(mView.getEmail());
        request.setGender(mView.getGender());
        request.setContactNo(mView.getContactNo());
        request.setContactNo(mView.getContactNo());
        request.setReferralCode(mView.getReferralCode());
        request.setLoginType(Constants.LOGIN_TYPE_CUSTOMER);
        request.setIsSocialMedia(Constants.SOCIAL_MEDIA_LOGIN_FALSE);

        if (validateFields(request)) {
            mInteractor.submitSignUp(this, request);
            return;
        }
        mView.hideProgressView();
    }

    @Override
    public void onSignUpSuccess(Data data) {
        mView.hideProgressView();
        mView.signUpSuccess("Success",data);
    }

    @Override
    public void onSignUpFailure(String message) {
        mView.hideProgressView();
        mView.signUpFailed(message);
    }


}
