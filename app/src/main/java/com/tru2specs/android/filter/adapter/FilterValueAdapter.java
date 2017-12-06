package com.tru2specs.android.filter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.filter.listener.OnFilterValueClickListener;
import com.tru2specs.android.objects.responses.filter.Value;

import java.util.List;

/**
 * Created by palgour on 12/3/17.
 */

public class FilterValueAdapter extends RecyclerView.Adapter<FilterValueAdapter.FilterValueViewHolder> {

    //    private final OnUpdateAddressListener mListener;
    private Context mContext;
    private List<Value> mList;
    private static CheckBox lastDefault = null;
    private OnFilterValueClickListener mListener;
    private String mKey = "";

    public FilterValueAdapter(String key, Context context, List<Value> faqList, OnFilterValueClickListener listener) {
        this.mKey = key;
        this.mContext = context;
        this.mList = faqList;
        this.mListener = listener;
    }


    @Override
    public FilterValueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_filter_value_row, parent, false);

        return new FilterValueViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilterValueViewHolder holder, final int position) {
        final Value faq = mList.get(position);
        if (!TextUtils.isEmpty(faq.getName())) {
            holder.mValueCheck.setChecked(faq.isChecked());

            holder.mValue.setText(faq.getName());//+ "(" + faq.getAvailableQty() + ")");
            holder.mValueCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mListener.onFilterValueClick(mKey, faq.getName(), isChecked);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class FilterValueViewHolder extends RecyclerView.ViewHolder {
        TextView mValue;
        CheckBox mValueCheck;

        public FilterValueViewHolder(View itemView) {
            super(itemView);
            mValue = (TextView) itemView.findViewById(R.id.txt_filter_value);
            mValueCheck = (CheckBox) itemView.findViewById(R.id.chk_value);
        }
    }
}