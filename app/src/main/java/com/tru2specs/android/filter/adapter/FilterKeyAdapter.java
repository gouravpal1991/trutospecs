package com.tru2specs.android.filter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.filter.listener.OnFilterKeyClickListener;
import com.tru2specs.android.objects.responses.filter.FilterItem;

import java.util.List;

/**
 * Created by palgour on 12/3/17.
 */

public class FilterKeyAdapter extends RecyclerView.Adapter<FilterKeyAdapter.FilterKeyViewHolder> {
    private Context mContext;
    private List<FilterItem> mList;
    private OnFilterKeyClickListener mListener;

    public FilterKeyAdapter(Context context, List<FilterItem> faqList, OnFilterKeyClickListener listener) {
        this.mContext = context;
        this.mList = faqList;
        this.mListener = listener;
    }


    @Override
    public FilterKeyAdapter.FilterKeyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_filter_row, parent, false);

        return new FilterKeyAdapter.FilterKeyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilterKeyViewHolder holder, final int position) {
        final FilterItem filterItem = mList.get(position);
        holder.mKey.setText(filterItem.getLabel());
        holder.mKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFilterKeyClick(filterItem.getLabel(), filterItem.getKey());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class FilterKeyViewHolder extends RecyclerView.ViewHolder {
        TextView mKey;

        public FilterKeyViewHolder(View itemView) {
            super(itemView);
            mKey = (TextView) itemView.findViewById(R.id.txt_filter_key);
        }
    }
}
