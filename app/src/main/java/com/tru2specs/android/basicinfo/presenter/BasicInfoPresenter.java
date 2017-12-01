package com.tru2specs.android.basicinfo.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.tru2specs.android.basicinfo.listener.OnBasicInfoFinishedListener;
import com.tru2specs.android.basicinfo.model.BasicInfoInteractor;
import com.tru2specs.android.basicinfo.view.IBasicInfoView;
import com.tru2specs.android.objects.User;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public class BasicInfoPresenter implements IBasicInfoPresenter, OnBasicInfoFinishedListener {

    private final IBasicInfoView mView;
    private final BasicInfoInteractor mInteractor;
    private final Context mContext;

    public BasicInfoPresenter(Context context, IBasicInfoView view) {
        this.mContext = context;
        this.mView = view;
        this.mInteractor = new BasicInfoInteractor();
    }

    @Override
    public boolean validateFields(User user) {
        if (TextUtils.isEmpty(user.getFullName())) {
            mView.basicInfoFailed("Please enter full name.");
            return false;
        }

        if (TextUtils.isEmpty(user.getContactNo())) {
            mView.basicInfoFailed("Please enter contact number.");
            return false;
        }

        if (!mView.getTermsAndConditionCheckStatus()) {
            mView.basicInfoFailed("Please accept terms and condition.");
            return false;
        }
        return true;
    }

    @Override
    public void attemptBasicInfoSubmission() {
        mView.showProgressView();

        User user = new User();
        user.setFullName(mView.getFullName());
        user.setContactNo(mView.getContactNo());

        if (validateFields(user)) {
            mInteractor.submitBasicInfo(mContext, this, user);
            return;
        }
        mView.hideProgressView();
    }


    @Override
    public void onBasicInfoSuccess() {
        mView.navigateToDashboard();
    }

    @Override
    public void onBasicInfoFailure(String message) {
        mView.basicInfoFailed(message);
    }
}
