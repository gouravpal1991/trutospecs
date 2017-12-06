package com.tru2specs.android.lens.listener;


import com.tru2specs.android.objects.responses.lens.Data;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public interface OnGetLensesListener {
    void onGetLensesSuccess(Data data);
    void onGetLensesFailure(String message);

    void onUpdateAddressSuccess(com.tru2specs.android.objects.responses.address.Data data);
    void onUpdateAddressFailure(String message);
}
