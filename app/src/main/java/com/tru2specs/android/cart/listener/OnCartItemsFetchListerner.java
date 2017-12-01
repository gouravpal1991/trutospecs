package com.tru2specs.android.cart.listener;

import com.tru2specs.android.objects.responses.productlisting.Product;

import java.util.ArrayList;

/**
 * Created by palgour on 11/19/17.
 */

public interface OnCartItemsFetchListerner {
    void onFetchSuccess(ArrayList<Product> products);
    void onFetchFailure(String msg);
}
