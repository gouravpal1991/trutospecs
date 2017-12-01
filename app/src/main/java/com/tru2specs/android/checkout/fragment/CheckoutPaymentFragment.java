package com.tru2specs.android.checkout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tru2specs.android.R;
import com.tru2specs.android.checkout.CheckoutActivity;
import com.tru2specs.android.objects.responses.savedaddresses.Address;

/**
 * Created by palgour on 8/26/17.
 */

public class CheckoutPaymentFragment extends Fragment {

    // Hint provided by the app that this fragment is currently visible to the user.
    boolean mUserVisibleHint = true;


    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private TextView mAddress;

    public static final CheckoutPaymentFragment newInstance(String message) {
        CheckoutPaymentFragment f = new CheckoutPaymentFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUserVisibleHint(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        View v = inflater.inflate(R.layout.fragment_checkout_payment, container, false);
//        TextView messageTextView = (TextView)v.findViewById(R.id.textView);
//        messageTextView.setText(message);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAddress = (TextView) view.findViewById(R.id.txt_checkout_address);
        init();
    }

    private void init() {

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getActivity() != null) {
            if (isVisibleToUser) {
                Address address = ((CheckoutActivity) getActivity()).getAddress();
                if (address != null) {
                    String formattedAddress = address.getName() + "\n" + address.getLine1() + "\n" + address.getLine2() + "\n" + address.getLandmark() + "\n" + address.getCity() + ", " + address.getState() + " - " + address.getPinCode();
                    String contactNo = "\n" + address.getPhoneNo1();
                    if (!TextUtils.isEmpty(contactNo) && !TextUtils.isEmpty(address.getPhoneNo2())) {
                        contactNo += ", " + address.getPhoneNo2();
                    }
                    formattedAddress += contactNo;
                    mAddress.setText(formattedAddress);
                }
            }
        }
    }
}
