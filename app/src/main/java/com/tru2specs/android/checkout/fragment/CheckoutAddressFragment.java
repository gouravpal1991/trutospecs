package com.tru2specs.android.checkout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tru2specs.android.MyApplication;
import com.tru2specs.android.R;
import com.tru2specs.android.address.AddressActivity;
import com.tru2specs.android.address.adapter.SavedAddressesAdapter;
import com.tru2specs.android.address.listener.OnUpdateAddressListener;
import com.tru2specs.android.address.presenter.SavedAddressesPresenter;
import com.tru2specs.android.address.view.ISavedAddressesView;
import com.tru2specs.android.checkout.CheckoutActivity;
import com.tru2specs.android.objects.request.AddressRequest;
import com.tru2specs.android.objects.responses.savedaddresses.Address;
import com.tru2specs.android.objects.responses.savedaddresses.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by palgour on 8/26/17.
 */

public class CheckoutAddressFragment extends Fragment implements ISavedAddressesView, OnUpdateAddressListener, View.OnClickListener {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    RecyclerView mAddresses;
    private SavedAddressesPresenter mPresenter;
    private Button mSaveAndContinue;
    private SavedAddressesAdapter addressesAdapter;
    private Button mButtonAddNewAddress;

    public static final CheckoutAddressFragment newInstance(String message) {
        CheckoutAddressFragment f = new CheckoutAddressFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        View v = inflater.inflate(R.layout.fragment_checkout_address, container, false);
//        TextView messageTextView = (TextView)v.findViewById(R.id.textView);
//        messageTextView.setText(message);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAddresses = (RecyclerView) view.findViewById(R.id.rv_checkout_address);
        mSaveAndContinue = (Button) view.findViewById(R.id.btn_chkout_save_and_continue);
        mButtonAddNewAddress = (Button)view.findViewById(R.id.btn_chkout_add_new_address);
        mSaveAndContinue.setOnClickListener(this);
        mButtonAddNewAddress.setOnClickListener(this);
        init();
    }


    public void init() {
        mPresenter = new SavedAddressesPresenter(getActivity(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mAddresses.setLayoutManager(mLayoutManager);
        mAddresses.setItemAnimator(new DefaultItemAnimator());

       // getAddresses();
        displaySavedAddress();
    }

    @Override
    public void onResume() {
        super.onResume();
        displaySavedAddress();
    }

    @Override
    public void getAddresses() {
        mPresenter.getSavedAddresses();
    }

    @Override
    public void hideProgressView() {
        ((CheckoutActivity) getActivity()).hideProgressView();
    }

    @Override
    public void showProgressView() {
        ((CheckoutActivity) getActivity()).showProgressView();
    }

    @Override
    public void onGetAddressesFailure(String msg) {

    }

    @Override
    public void onGetAddressesSuccess(Data data) {
        List<Address> addresses = data.getAddress();
        addressesAdapter = new SavedAddressesAdapter(getActivity(), addresses, this);
        mAddresses.setAdapter(addressesAdapter);
        addressesAdapter.notifyDataSetChanged();
        hideProgressView();
    }

    @Override
    public void updateAddress(Address address) {
        ((CheckoutActivity) getActivity()).setAddress(address);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_chkout_save_and_continue:
               /* if (((CheckoutActivity) getActivity()).getAddress() == null) {
                    Toast.makeText(getActivity(), "Please select or add a new address.", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                ((CheckoutActivity)getActivity()).goToNextPagerSlide();
                break;
            case R.id.btn_chkout_add_new_address:
                navigateToAddAddressActivity();
        }
    }

    private void navigateToAddAddressActivity() {
        Intent addressIntent = new Intent(getActivity(), AddressActivity.class);
        startActivity(addressIntent);
    }

    // temporally display address from application class
    private void displaySavedAddress() {

        hideProgressView();

        if(MyApplication.getInstance().getUserAddressList().size() > 0) {
            List<Address> addresses = new ArrayList<Address>();

            for(AddressRequest addressRequest : MyApplication.getInstance().getUserAddressList()) {

                //AddressRequest addressRequest = MyApplication.getInstance().getUserAddressList().get(0);
                if (addressRequest != null) {

                    Address address = new Address();
                    address.setName(addressRequest.getName());
                    address.setLine1(addressRequest.getLine1());
                    address.setLine2(addressRequest.getLine2());
                    address.setPhoneNo1(addressRequest.getPhone1());
                    address.setPhoneNo2(addressRequest.getPhone2());
                    address.setPinCode(addressRequest.getPincode());
                    address.setCity(addressRequest.getCity());
                    address.setState(addressRequest.getState());
                    address.setCountry(addressRequest.getCountry());
                    address.setLandmark(addressRequest.getLandmark());
                    address.setIsDefault(1);
                    addresses.add(address);

                }
            }

            addressesAdapter = new SavedAddressesAdapter(getActivity(), addresses, this);
            mAddresses.setAdapter(addressesAdapter);
            addressesAdapter.notifyDataSetChanged();
        }
    }
}
