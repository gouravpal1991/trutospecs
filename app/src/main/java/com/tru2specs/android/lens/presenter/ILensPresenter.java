package com.tru2specs.android.lens.presenter;

import com.tru2specs.android.objects.responses.savedaddresses.Address;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public interface ILensPresenter {
    void getSavedAddresses();
    void updateAddress(Address address);
}
