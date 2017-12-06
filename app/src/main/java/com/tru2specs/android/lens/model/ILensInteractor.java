package com.tru2specs.android.lens.model;

import android.content.Context;

import com.tru2specs.android.address.listener.OnSavedAddressesListener;
import com.tru2specs.android.lens.listener.OnGetLensesListener;
import com.tru2specs.android.objects.request.LensRequest;
import com.tru2specs.android.objects.request.SavedAddressesRequest;
import com.tru2specs.android.objects.responses.savedaddresses.Address;

/**
 * Created by ajaykumarsantra on 19/08/17.
 */

public interface ILensInteractor {
    void onGetLenses(Context context, OnGetLensesListener listener, LensRequest request);

    void onUpdateAddress(Context context, OnGetLensesListener listener, LensRequest request);
}
