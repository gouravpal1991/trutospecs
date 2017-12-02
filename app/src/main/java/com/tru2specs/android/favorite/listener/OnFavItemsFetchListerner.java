package com.tru2specs.android.favorite.listener;

import com.tru2specs.android.objects.responses.productlisting.Product;

import java.util.ArrayList;

/**
 * Created by Selva.
 */

public interface OnFavItemsFetchListerner {
    void onFetchSuccess(ArrayList<Product> products);
    void onFetchFailure(String msg);
}
