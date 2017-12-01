package com.tru2specs.android.productdetails.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tru2specs.android.R;

/**
 * Created by palgour on 12/1/17.
 */

public class UseForViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView mAddressLine1;
    TextView mAddressLine2;
    TextView mCityAndPin;
    TextView mContact;
    TextView mDistance;

    public UseForViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mAddressLine1 = (TextView) itemView.findViewById(R.id.txt_store_address_line_1);
        mAddressLine2 = (TextView) itemView.findViewById(R.id.txt_store_address_line_2);
        mCityAndPin = (TextView) itemView.findViewById(R.id.txt_store_address_city_and_pin);
        mContact = (TextView) itemView.findViewById(R.id.txt_store_address_contact);
        mDistance = (TextView) itemView.findViewById(R.id.txt_store_address_distance);
    }

    @Override
    public void onClick(View v) {

    }
}
