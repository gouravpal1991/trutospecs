package com.tru2specs.android.productdetails.presenter;

import com.tru2specs.android.objects.request.AddressRequest;
import com.tru2specs.android.objects.request.ProudctListingRequest;

/**
 * Created by palgour on 11/17/17.
 */

public interface IProductDetailsPresenter {
    boolean validateFields(ProudctListingRequest request);
    void attemptGetProductDetails();
}
