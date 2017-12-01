package com.tru2specs.android.store.presenter;

import android.support.v7.app.AppCompatActivity;

import com.tru2specs.android.objects.responses.store.StoreAddressData;
import com.tru2specs.android.store.listener.OnGetStoreListener;
import com.tru2specs.android.store.model.StoreInteractor;
import com.tru2specs.android.store.view.IStoreView;

/**
 * Created by GP00471911 on 28-06-2017.
 */

public class StorePresenter extends AppCompatActivity implements IStorePresenter, OnGetStoreListener {

    private final IStoreView mView;
    private final StoreInteractor mInteractor;

    public StorePresenter(IStoreView view) {
        this.mView = view;
        this.mInteractor = new StoreInteractor();
    }

    @Override
    public void getStores() {
        mView.showProgressView();
        mInteractor.fetchStoresList(this);
    }

    @Override
    public void onSuccess(StoreAddressData data) {
        mView.storeListFetchingSuccess(data.getStoreAddress());
        mView.hideProgressView();
    }

    @Override
    public void onFailure(String message) {
        mView.hideProgressView();
        mView.storeListFetchingFailed(message);
    }
}
