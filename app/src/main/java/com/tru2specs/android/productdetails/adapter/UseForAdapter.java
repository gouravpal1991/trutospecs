package com.tru2specs.android.productdetails.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;
import com.tru2specs.android.R;
import com.tru2specs.android.cart.CartActivity;
import com.tru2specs.android.lens.LensesActivity;
import com.tru2specs.android.objects.responses.store.StoreAddress;
import com.tru2specs.android.objects.responses.useFor.Item;
import com.tru2specs.android.productdetails.view.IProducDetailsView;
import com.tru2specs.android.util.Helper;

import java.util.List;

/**
 * Created by palgour on 12/1/17.
 */

public class UseForAdapter extends RecyclerView.Adapter<UseForAdapter.UseForViewHolder> {
    private final Context mContext;
    List<Item> mItems;
    IProducDetailsView mView;

    public UseForAdapter(Context context, List<Item> items, IProducDetailsView view) {
        this.mItems = items;
        this.mContext = context;
        this.mView=view;
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
        useForViewHolder.mUseFor.setText(object.getUseFor());
        useForViewHolder.mDesc1.setText(object.getDesc1());
        useForViewHolder.mDesc2.setText(object.getDesc2());
        if (!TextUtils.isEmpty(object.getImg()))
            Helper.downloadAndSetImage(mContext, useForViewHolder.mImgUseFor, object.getImg());

    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public class UseForViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mUseFor;
        TextView mDesc1;
        TextView mDesc2;
        ImageView mImgUseFor;
        LinearLayout mParent;

        public UseForViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mParent = (LinearLayout) itemView.findViewById(R.id.lin_use_for_parent);
            mUseFor = (TextView) itemView.findViewById(R.id.txt_use_for);
            mDesc1 = (TextView) itemView.findViewById(R.id.txt_use_desc_1);
            mDesc2 = (TextView) itemView.findViewById(R.id.txt_use_desc_2);
            mImgUseFor = (ImageView) itemView.findViewById(R.id.img_use_for);
            mParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.lin_use_for_parent:
                    mView.navigateToLensActivity();
                    break;
            }

        }
    }
}
