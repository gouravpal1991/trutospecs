package com.tru2specs.android.address.presenter;

import com.tru2specs.android.objects.responses.savedaddresses.Address;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public interface ISavedAddressesPresenter {
    void getSavedAddresses();
    void updateAddress(Address address);
}
