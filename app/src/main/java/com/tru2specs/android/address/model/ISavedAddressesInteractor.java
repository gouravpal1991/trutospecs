package com.tru2specs.android.address.model;

import com.tru2specs.android.address.listener.OnAddressSaveListener;
import com.tru2specs.android.address.listener.OnSavedAddressesListener;
import com.tru2specs.android.objects.request.AddressRequest;
import com.tru2specs.android.objects.request.SavedAddressesRequest;
import com.tru2specs.android.objects.responses.savedaddresses.Address;

/**
 * Created by ajaykumarsantra on 19/08/17.
 */

public interface ISavedAddressesInteractor {
    void onGetSavedAddresses(OnSavedAddressesListener listener, SavedAddressesRequest request);
    void onUpdateAddress(OnSavedAddressesListener listener, Address request);
}
