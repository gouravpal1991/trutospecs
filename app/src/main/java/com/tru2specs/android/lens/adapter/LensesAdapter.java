package com.tru2specs.android.lens.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.address.listener.OnUpdateAddressListener;
import com.tru2specs.android.lens.view.ILensesView;
import com.tru2specs.android.objects.responses.lens.Lenses;
import com.tru2specs.android.util.Constants;

import java.util.List;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public class LensesAdapter extends RecyclerView.Adapter<LensesAdapter.LensViewHolder> {

//    private final OnUpdateAddressListener mListener;
    private Context mContext;
    private List<Lenses> mList;
    private static RadioButton lastDefault = null;
    private static int lastDefaultPos = 0;
    ILensesView mView;

    public LensesAdapter(Context context, List<Lenses> addressList, ILensesView view) {
        this.mContext = context;
        this.mList = addressList;
        this.mView = view;
    }


    @Override
    public LensViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_lenses_row, parent, false);

        return new LensViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LensViewHolder holder, final int position) {
        final Lenses lens = mList.get(position);
        holder.mBrandName.setText(lens.getLensBrandName());
        holder.mActualAmt.setText(Constants.CURRENCY + lens.getPrice());
        holder.mLensName.setText(lens.getLensName());
        String desc = mContext.getString(R.string.bullet)+" "+ lens.getLensDesc().replace("\n","\n"+mContext.getString(R.string.bullet)+" ");
        holder.mLensDesc.setText(desc);
        holder.mBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.addProductInCart();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class LensViewHolder extends RecyclerView.ViewHolder {
        TextView mBrandName;
        TextView mActualAmt;
        TextView mLensName;
        TextView mLensDesc;
        Button mBuyNow;

        public LensViewHolder(View itemView) {
            super(itemView);
            mBrandName = (TextView) itemView.findViewById(R.id.txt_lens_brand_name);
            mActualAmt = (TextView) itemView.findViewById(R.id.txt_lens_actual_amount);
            mLensName = (TextView) itemView.findViewById(R.id.txt_lens_name);
            mLensDesc = (TextView) itemView.findViewById(R.id.txt_lens_desc);
            mBuyNow = (Button) itemView.findViewById(R.id.btn_lens_buy_now);
        }
    }
}
