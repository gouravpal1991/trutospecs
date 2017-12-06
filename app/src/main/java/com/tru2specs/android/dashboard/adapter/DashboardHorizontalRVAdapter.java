package com.tru2specs.android.dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tru2specs.android.R;
import com.tru2specs.android.objects.responses.dashboard.DashboardProduct;
import com.tru2specs.android.productdetails.ProductDetailsActivity;

import java.util.List;

/**
 * Created by palgour on 11/4/17.
 */

public class DashboardHorizontalRVAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<DashboardProduct> mDataList;
    private int mRowIndex = -1;
private Context mContext;
    public DashboardHorizontalRVAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<DashboardProduct> data) {
        if (mDataList != data) {
            mDataList = data;
            notifyDataSetChanged();
        }
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rlDashboardItem;
        private TextView itemName;
        private ImageView itemImage;

        public ItemViewHolder(View itemView) {
            super(itemView);
            rlDashboardItem = (RelativeLayout) itemView.findViewById(R.id.rl_dashboard_horizontal_item_parent);
            itemName = (TextView) itemView.findViewById(R.id.txt_item_name);
            itemImage = (ImageView) itemView.findViewById(R.id.img_item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_dashboard_item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder rawholder, int position) {
        ItemViewHolder holder = (ItemViewHolder) rawholder;
        final DashboardProduct product = mDataList.get(position);
        holder.itemName.setText(product.getProdName());
        holder.rlDashboardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToProductDetails(product.getProdId());
            }
        });
        downloadAndSetImage(mContext,holder.itemImage,product.getProdImg());
    }

    private void navigateToProductDetails(String prodId) {

        Intent intent = new Intent(mContext, ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_ID,prodId);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static void downloadAndSetImage(Context context,
                                           ImageView view, String url) {
        Glide.with(context).load(url)
                .fitCenter()
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }


}
