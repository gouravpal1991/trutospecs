package com.tru2specs.android.store.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;
import com.tru2specs.android.R;
import com.tru2specs.android.objects.responses.store.StoreAddress;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by GP00471911 on 28-06-2017.
 */

public class StoreListItemAdapter extends RecyclerView.Adapter<StoreRowViewHolder> {

    private final Context mContext;
    List<StoreAddress> mStores;

    public StoreListItemAdapter(Context context, List<StoreAddress> items) {
        this.mStores = items;
        this.mContext = context;
    }

    @Override
    public StoreRowViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_store_row, null);
        StoreRowViewHolder rcv = new StoreRowViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(StoreRowViewHolder storeRowViewHolder, int i) {
        StoreAddress object = mStores.get(i);
        if (TextUtils.isEmpty(object.getAddressLine1())) {
            storeRowViewHolder.mAddressLine1.setVisibility(View.GONE);
        } else {
            storeRowViewHolder.mAddressLine1.setText(object.getAddressLine1());
        }

        if (TextUtils.isEmpty(object.getAddressLine2())) {
            storeRowViewHolder.mAddressLine2.setVisibility(View.GONE);
        } else {
            storeRowViewHolder.mAddressLine2.setText(object.getAddressLine2());
        }

        if (TextUtils.isEmpty(object.getCity())) {
            storeRowViewHolder.mCityAndPin.setVisibility(View.GONE);
        } else {
            storeRowViewHolder.mCityAndPin.setText(object.getCity() + ", " + object.getPincode());
        }

        if (TextUtils.isEmpty(object.getContact())) {
            storeRowViewHolder.mContact.setVisibility(View.GONE);
        } else {
            storeRowViewHolder.mContact.setText(object.getContact());
        }


        //TODO:replace with current location
        LatLng currentLatLng = new LatLng(1.3521, 103.8198);
        LatLng storeLatLng = new LatLng(Double.valueOf(object.getLatitude()), Double.valueOf(object.getLongitude()));

        Double distance = SphericalUtil.computeDistanceBetween(currentLatLng, storeLatLng);
        distance = distance / 1000;
        storeRowViewHolder.mDistance.setText(String.format("%.2f", distance) + " kms");
    }

    @Override
    public int getItemCount() {
        return this.mStores.size();
    }
}
