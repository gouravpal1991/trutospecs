package com.tru2specs.android.productdetails.view;

import com.tru2specs.android.objects.request.ProudctListingRequest;
import com.tru2specs.android.objects.responses.productlisting.Data;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.objects.responses.productlisting.ProductListingResponse;

/**
 * Created by palgour on 11/17/17.
 */

public interface IProducDetailsView {
    void showProgress();
    void hideProgress();
    void setToolbar();
    void onError(String msg);
    void onSuccess(Data response);
    void getProductDetails(Product product);
void setProductDetails(Product product);
    String getProductId();
}

