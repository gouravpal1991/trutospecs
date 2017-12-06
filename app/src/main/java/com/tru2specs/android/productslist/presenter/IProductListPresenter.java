package com.tru2specs.android.productslist.presenter;

import com.tru2specs.android.objects.request.filterrequest.FilterRequest;

/**
 * Created by PB00471065 on 11/16/2017.
 */

public interface IProductListPresenter {
    void getProductData();
    void getProducts(FilterRequest request);
    void doLogin();
}
