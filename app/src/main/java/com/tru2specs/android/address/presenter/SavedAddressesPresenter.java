package com.tru2specs.android.address.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.tru2specs.android.address.listener.OnSavedAddressesListener;
import com.tru2specs.android.address.model.SavedAddressesInteractor;
import com.tru2specs.android.address.view.IAddressView;
import com.tru2specs.android.address.view.ISavedAddressesView;
import com.tru2specs.android.manager.SessionManager;
import com.tru2specs.android.objects.request.SavedAddressesRequest;
import com.tru2specs.android.objects.responses.savedaddresses.Address;
import com.tru2specs.android.objects.responses.savedaddresses.Data;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public class SavedAddressesPresenter implements ISavedAddressesPresenter, OnSavedAddressesListener {

    private ISavedAddressesView mView;
    private Context mContext;
    private SavedAddressesInteractor mInteractor;

    public SavedAddressesPresenter(Context context, ISavedAddressesView view) {
        mView = view;
        mContext = context;
        mInteractor = new SavedAddressesInteractor();
    }

    @Override
    public void getSavedAddresses() {
        mView.showProgressView();
        com.tru2specs.android.objects.responses.loginSignup.Data data =
                new Gson().fromJson(new SessionManager(mContext).getUserInfo(), com.tru2specs.android.objects.responses.loginSignup.Data.class);
        SavedAddressesRequest request = new SavedAddressesRequest();
        if (data != null && data.getUser() != null) {
            request.setUserId(data.getUser().getUserId());
            mInteractor.onGetSavedAddresses(this, request);
            return;
        }
        mView.hideProgressView();
    }

    @Override
    public void updateAddress(Address address) {
        mView.showProgressView();
        mInteractor.onUpdateAddress(this, address);
    }

    @Override
    public void onSavedAddressSuccess(Data data) {

        mView.onGetAddressesSuccess(data);
    }

    @Override
    public void onSavedAddressFailure(String message) {
        mView.hideProgressView();
        mView.onGetAddressesFailure(message);
    }

    @Override
    public void onUpdateAddressSuccess(com.tru2specs.android.objects.responses.address.Data data) {
        mView.hideProgressView();
        //failure method is used to display toast
       mView.onGetAddressesFailure(data.getUser().getMessage());
    }

    @Override
    public void onUpdateAddressFailure(String message) {
        mView.hideProgressView();
    }
}
