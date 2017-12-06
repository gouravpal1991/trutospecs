package com.tru2specs.android.lens.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.tru2specs.android.lens.listener.OnGetLensesListener;
import com.tru2specs.android.lens.model.LensInteractor;
import com.tru2specs.android.lens.view.ILensesView;
import com.tru2specs.android.manager.SessionManager;
import com.tru2specs.android.objects.request.LensRequest;
import com.tru2specs.android.objects.responses.savedaddresses.Address;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public class LensPresenter implements ILensPresenter, OnGetLensesListener {

    private ILensesView mView;
    private Context mContext;
    private LensInteractor mInteractor;

    public LensPresenter(Context context, ILensesView view) {
        mView = view;
        mContext = context;
        mInteractor = new LensInteractor();
    }

    @Override
    public void getSavedAddresses() {
        mView.showProgressView();
        LensRequest request = new LensRequest();
        mInteractor.onGetLenses(mContext, this, request);
    }

    @Override
    public void updateAddress(Address address) {

    }

    @Override
    public void onGetLensesSuccess(com.tru2specs.android.objects.responses.lens.Data data) {
        mView.onGetLensSuccess(data);
    }

    @Override
    public void onGetLensesFailure(String message) {
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
