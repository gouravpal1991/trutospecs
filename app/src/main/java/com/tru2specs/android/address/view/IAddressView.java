package com.tru2specs.android.address.view;

/**
 * Created by ajaykumarsantra on 19/08/17.
 */

public interface IAddressView {
    void hideProgressView();
    void showProgressView();
    String getName();
    String getUserId();
    String getLine1();
    String getLine2();
    String getLine3();
    String getLandmark();
    String getState();
    String getCity();
    String getPincode();
    String getCountry();
    String getPhone1();
    String getPhone2();
    boolean isDefault();
    void navigateToMyAccount();
    void navigateToSavedAddresses();
    void onAddAddressFaliure(String string);
    void onAddAddressSuccess(String string);
}
