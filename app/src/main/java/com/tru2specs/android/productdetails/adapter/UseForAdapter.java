package com.tru2specs.android.productdetails.adapter;

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
import com.tru2specs.android.objects.responses.useFor.Item;

import java.util.List;

/**
 * Created by palgour on 12/1/17.
 */

public class UseForAdapter extends RecyclerView.Adapter<UseForViewHolder> {
    private final Context mContext;
    List<Item> mItems;

    public UseForAdapter(Context context, List<Item> items) {
        this.mItems = items;
        this.mContext = context;
    }

    @Override
    public UseForViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_use_for_row, null);
        UseForViewHolder rcv = new UseForViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(UseForViewHolder useForViewHolder, int i) {
        Item object = mItems.get(i);


    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }
}
