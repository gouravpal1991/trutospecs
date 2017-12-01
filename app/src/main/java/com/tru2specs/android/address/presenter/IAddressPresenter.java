package com.tru2specs.android.address.presenter;


import com.tru2specs.android.objects.request.AddressRequest;

/**
 * Created by ajaykumarsantra on 19/08/17.
 */

public interface IAddressPresenter {
    boolean validateFields(AddressRequest address);
    void attemptAddressSubmission();
}
