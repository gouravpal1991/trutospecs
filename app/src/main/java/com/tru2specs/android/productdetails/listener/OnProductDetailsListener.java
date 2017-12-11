package com.tru2specs.android.productdetails.listener;

import com.tru2specs.android.objects.responses.productlisting.Data;
import com.tru2specs.android.objects.responses.productlisting.ProductListingResponse;

/**
 * Created by palgour on 11/17/17.
 */

public interface OnProductDetailsListener {
    void onSuccess(Data response);
    void onFailure(String msg);
}
