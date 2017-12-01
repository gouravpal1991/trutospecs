package com.tru2specs.android.address.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.address.listener.OnUpdateAddressListener;
import com.tru2specs.android.objects.responses.address.AddressResponse;
import com.tru2specs.android.objects.responses.savedaddresses.Address;
import com.tru2specs.android.objects.responses.savedaddresses.SavedAddressesResponse;
import com.tru2specs.android.rest.APIService;
import com.tru2specs.android.rest.AppClient;

import java.util.List;

import retrofit2.Call;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public class SavedAddressesAdapter extends RecyclerView.Adapter<SavedAddressesAdapter.AddressViewHolder> {

    private final OnUpdateAddressListener mListener;
    private Context mContext;
    private List<Address> mList;
    private static RadioButton lastDefault = null;
    private static int lastDefaultPos = 0;

    public SavedAddressesAdapter(Context context, List<Address> addressList, OnUpdateAddressListener listener) {
        this.mContext = context;
        this.mList = addressList;
        this.mListener = listener;
    }


    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_address_row, parent, false);

        return new SavedAddressesAdapter.AddressViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, final int position) {
        final Address address = mList.get(position);
        String formattedAddress = address.getLine1() + "\n" + address.getLine2() + "\n" + address.getLandmark() + "\n" + address.getCity() + ", " + address.getState() + " - " + address.getPinCode();
        holder.mName.setText(address.getName());
        holder.mAddress.setText(formattedAddress);
        if (address.getIsDefault() == 1) {
            holder.mDefault.setChecked(true);
            holder.mDefault.setTag(new Integer(position));
            lastDefault = holder.mDefault;
            lastDefaultPos = position;
            mListener.updateAddress(address);
        } else {
            holder.mDefault.setChecked(false);
        }
        String contactNo = address.getPhoneNo1();
        if (!TextUtils.isEmpty(contactNo) && !TextUtils.isEmpty(address.getPhoneNo2())) {
            contactNo += ", " + address.getPhoneNo2();
        }
        holder.mContact.setText(contactNo);


        holder.mDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                int checkedPos = 0;
                if (rb.getTag() != null) {
                    checkedPos = ((Integer) rb.getTag()).intValue();
                } else {
                    checkedPos = position;
                }
                if (lastDefaultPos != checkedPos) {
                    if (rb.isChecked()) {
                        if (lastDefault != null) {
                            lastDefault.setChecked(false);
                            mList.get(lastDefaultPos).setIsDefault(0);
                        }
                        lastDefault = rb;
                        lastDefaultPos = checkedPos;
                    }


                    if (rb.isChecked())
                        mList.get(checkedPos).setIsDefault(1);
                    else
                        mList.get(checkedPos).setIsDefault(0);

                    mListener.updateAddress(address);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        TextView mName;
        TextView mAddress;
        TextView mContact;
        RadioButton mDefault;

        public AddressViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.txt_name);
            mAddress = (TextView) itemView.findViewById(R.id.txt_address_lines);
            mContact = (TextView) itemView.findViewById(R.id.txt_contact_number);
            mDefault = (RadioButton) itemView.findViewById(R.id.rb_default);
        }
    }
}
