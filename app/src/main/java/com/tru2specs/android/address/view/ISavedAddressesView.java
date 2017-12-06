package com.tru2specs.android.address.view;

import com.tru2specs.android.objects.responses.savedaddresses.Data;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public interface ISavedAddressesView {
    void getAddresses();

    void hideProgressView();

    void showProgressView();
    void onGetAddressesFailure(String msg);
    void onGetAddressesSuccess(Data data);
}
