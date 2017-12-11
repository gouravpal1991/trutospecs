package com.tru2specs.android.checkout.view;

import android.support.v4.app.Fragment;

import com.tru2specs.android.objects.responses.savedaddresses.Address;

import java.util.List;

/**
 * Created by palgour on 8/25/17.
 */

public interface ICheckoutView {
    void showProgressView();

    void hideProgressView();

    void setScreenTitle();

    void init();

    List<Fragment> getFragments();

    void createCustomTabs();

    void setAddress(Address address);

    Address getAddress();

    void goToNextPagerSlide();
}
