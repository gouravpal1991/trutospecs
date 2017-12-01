package com.tru2specs.android.profile.presenter;

import android.text.TextUtils;

import com.tru2specs.android.objects.request.UpdateUserRequest;
import com.tru2specs.android.objects.responses.loginSignup.Data;
import com.tru2specs.android.profile.listener.OnUpdateUserFinishedListener;
import com.tru2specs.android.profile.model.ProfileInteractor;
import com.tru2specs.android.profile.view.IProfileView;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public class ProfilePresenter implements IProfilePresenter, OnUpdateUserFinishedListener {

    private final IProfileView mView;
    private final ProfileInteractor mInteractor;

    public ProfilePresenter(IProfileView view) {
        this.mView = view;
        this.mInteractor = new ProfileInteractor();
    }


    @Override
    public boolean validateFields(UpdateUserRequest updateUserRequest) {
        if (TextUtils.isEmpty(updateUserRequest.getContactNo())) {
            mView.updateUserFailed("Please enter contact number.");
            return false;
        }

        return true;
    }

    @Override
    public void attempUpdateUser() {
        mView.showProgressView();

        UpdateUserRequest request = new UpdateUserRequest();
        request.setUserId(mView.getEmail());
        request.setContactNo(mView.getContactNo());
        request.setGender(mView.getGender());
        request.setDOB(mView.getDob());

        if (validateFields(request)) {
            mInteractor.submitProfileUpdate(this, request);
            return;
        }
        mView.hideProgressView();
    }




    @Override
    public void onUpdateUserSucces(Data data) {
        mView.hideProgressView();
        mView.updateUserSuccess("Details updated successfully!",data);
    }

    @Override
    public void onUpdateUserFailure(String message) {
        mView.hideProgressView();
        mView.updateUserFailed(message);
    }
}
