package com.tru2specs.android.productdetails.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.tru2specs.android.objects.request.ProudctListingRequest;
import com.tru2specs.android.objects.responses.productlisting.Data;
import com.tru2specs.android.productdetails.interactor.ProductDetailsInteractor;
import com.tru2specs.android.productdetails.listener.OnProductDetailsListener;
import com.tru2specs.android.productdetails.view.IProducDetailsView;

/**
 * Created by palgour on 11/17/17.
 */

public class ProductDetailsPresenter implements IProductDetailsPresenter, OnProductDetailsListener {

    ProductDetailsInteractor mInteractor;
    Context mContext;
    private final IProducDetailsView mView;

    public ProductDetailsPresenter(Context context, IProducDetailsView view) {
        mContext = context;
        mInteractor = new ProductDetailsInteractor();
        mView = view;
    }

    @Override
    public boolean validateFields(ProudctListingRequest request) {
        if (TextUtils.isEmpty(request.getProductId())) {
            mView.onError("Product Id not found");
        }
        return true;
    }

    @Override
    public void attemptGetProductDetails() {
        ProudctListingRequest request = new ProudctListingRequest();
        request.setProductId(mView.getProductId());
        if (validateFields(request)) {
            mInteractor.getProductDetailsById(this, request);
        }
    }

    @Override
    public void onSuccess(Data response) {
        mView.onSuccess(response);
    }

    @Override
    public void onFailure(String msg) {

    }
}
