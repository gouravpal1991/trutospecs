package com.tru2specs.android.address.model;

import com.tru2specs.android.address.listener.OnAddressSaveListener;
import com.tru2specs.android.objects.request.AddressRequest;

/**
 * Created by ajaykumarsantra on 19/08/17.
 */

public interface IAddressInteractor {
    void onSubmitAddress(OnAddressSaveListener listener, AddressRequest request);
}
