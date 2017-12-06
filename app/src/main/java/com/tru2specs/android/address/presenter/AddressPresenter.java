package com.tru2specs.android.address.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.tru2specs.android.MyApplication;
import com.tru2specs.android.address.listener.OnAddressSaveListener;
import com.tru2specs.android.address.model.AddressInteractor;
import com.tru2specs.android.address.view.IAddressView;
import com.tru2specs.android.manager.SessionManager;
import com.tru2specs.android.objects.request.AddressRequest;
import com.tru2specs.android.objects.responses.address.Data;
import com.tru2specs.android.util.Constants;

/**
 * Created by ajaykumarsantra on 19/08/17.
 */

public class AddressPresenter implements IAddressPresenter, OnAddressSaveListener {

    Context mContext;
    private final IAddressView mView;
    private AddressInteractor mInteractor;

    public AddressPresenter(Context context, IAddressView view) {
        mContext = context;
        mView = view;
        mInteractor = new AddressInteractor();
    }


    @Override
    public boolean validateFields(AddressRequest request) {
        if (TextUtils.isEmpty(request.getName())) {
            mView.onAddAddressFaliure("Please enter full name.");
            return false;
        }
        if (TextUtils.isEmpty(request.getLine1()) && TextUtils.isEmpty(request.getLine2())) {
            mView.onAddAddressFaliure("Please enter address.");
            return false;
        }
        if (TextUtils.isEmpty(request.getLandmark())) {
            mView.onAddAddressFaliure("Please enter landmark.");
            return false;
        }
        if (TextUtils.isEmpty(request.getPhone1())) {
            mView.onAddAddressFaliure("Please enter mobile number.");
            return false;
        }

        if (TextUtils.isEmpty(request.getPincode())) {
            mView.onAddAddressFaliure("Please enter zip code.");
            return false;
        }


        if (TextUtils.isEmpty(request.getCountry())) {
            mView.onAddAddressFaliure("Please enter country.");
            return false;
        }

        return true;
    }

    @Override
    public void attemptAddressSubmission() {
        mView.showProgressView();
        AddressRequest request = new AddressRequest();
        request.setName(mView.getName());
        request.setCity(mView.getCity());
        request.setCountry(mView.getCountry());
        request.setLine1(mView.getLine1());
        request.setLine2(mView.getLine2());
        request.setPhone1(mView.getPhone1());
        request.setPhone2(mView.getPhone2());
        request.setLandmark(mView.getLandmark());
        request.setLandmark(mView.getLandmark());
        request.setState(mView.getState());
        request.setPincode(mView.getPincode());
        request.setUserType(Constants.USER_TYPE_CUSTOMER);

        com.tru2specs.android.objects.responses.loginSignup.Data data =
                new Gson().fromJson(new SessionManager(mContext).getUserInfo(), com.tru2specs.android.objects.responses.loginSignup.Data.class);
        if (data != null && data.getUser() != null) {
            request.setUserId(data.getUser().getUserId());
        }

        if (mView.isDefault()) {
            request.setIsDefault("1");
        } else {
            request.setIsDefault("0");
        }

        if (validateFields(request)) {
            mInteractor.onSubmitAddress(this, request);
            MyApplication.getInstance().saveUserAddress(request);
            return;
        }
        mView.hideProgressView();
    }

    @Override
    public void onAddAddressSuccess(Data data) {
        mView.hideProgressView();
        mView.onAddAddressSuccess(Constants.ADDRESS_ADDED_SUCCESSFULLY);

    }

    @Override
    public void onAddAddressFailure(String message) {
        mView.hideProgressView();
        mView.onAddAddressFaliure(message);
    }
}
