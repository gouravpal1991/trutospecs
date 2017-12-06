package com.tru2specs.android.address.listener;


import com.tru2specs.android.objects.responses.savedaddresses.Data;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public interface OnSavedAddressesListener {
    void onSavedAddressSuccess(Data data);
    void onSavedAddressFailure(String message);

    void onUpdateAddressSuccess(com.tru2specs.android.objects.responses.address.Data data);
    void onUpdateAddressFailure(String message);
}
