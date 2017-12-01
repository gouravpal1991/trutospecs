package com.tru2specs.android.productdetails.interactor;

import com.tru2specs.android.objects.request.ProudctListingRequest;
import com.tru2specs.android.productdetails.listener.OnProductDetailsListener;

/**
 * Created by palgour on 11/17/17.
 */

public interface IProductDetailsInteractor {
    void getProductDetailsById(OnProductDetailsListener listener, ProudctListingRequest request);
}
