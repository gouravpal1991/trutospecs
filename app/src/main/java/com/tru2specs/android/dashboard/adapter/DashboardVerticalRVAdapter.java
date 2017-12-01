package com.tru2specs.android.dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.objects.responses.dashboard.Type;
import com.tru2specs.android.productlisting.ProductListingActivity;
import com.tru2specs.android.productslist.ProductsListActivity;

import java.util.List;

/**
 * Created by palgour on 11/4/17.
 */

public class DashboardVerticalRVAdapter extends RecyclerView.Adapter<DashboardVerticalRVAdapter.SimpleViewHolder> {
    private final Context mContext;
    private static List<Type> mData;
    private static RecyclerView horizontalList;

    //TODO:Pass object of Product Category and ProductList in second parammeter
    //replace List<String> data with Product Category and ProductList object, values will be returned from Dashboard API
    public DashboardVerticalRVAdapter(Context context, List<Type> data) {
        mContext = context;
       mData=data;
    }

    @Override
    public DashboardVerticalRVAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dashboard_product_list_vertical_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DashboardVerticalRVAdapter.SimpleViewHolder holder, int position) {
        Type type = mData.get(position);
        holder.mCategoryNameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToProductListing();
            }
        });
        holder.mCategoryName.setText(type.getProductType());
        holder.mHorizontalAdapter.setData(type.getTagCategory());
        holder.mHorizontalAdapter.setRowIndex(position);
    }

    private void navigateToProductListing() {
        Intent intent = new Intent(mContext, ProductListingActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView mCategoryName;
        public  RelativeLayout mCategoryNameLayout;
        private DashboardHorizontalRVAdapter mHorizontalAdapter;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            Context context = itemView.getContext();
            mCategoryNameLayout = (RelativeLayout)itemView.findViewById(R.id.rl_dashboard_category_header);
            mCategoryName = (TextView) itemView.findViewById(R.id.txt_dashboard_item_category_name);
            horizontalList = (RecyclerView) itemView.findViewById(R.id.rv_dashboard_horizontal_list);
            horizontalList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            mHorizontalAdapter = new DashboardHorizontalRVAdapter(context);
            horizontalList.setAdapter(mHorizontalAdapter);
        }
    }
}
