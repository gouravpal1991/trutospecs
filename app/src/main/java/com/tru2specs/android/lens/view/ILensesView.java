package com.tru2specs.android.lens.view;


import com.tru2specs.android.objects.responses.lens.Data;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public interface ILensesView {
    void getAddresses();

    void hideProgressView();

    void showProgressView();
    void onGetAddressesFailure(String msg);
    void onGetLensSuccess(Data data);
    void addProductInCart();
}
