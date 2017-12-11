package com.tru2specs.android.dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.objects.responses.dashboard.DashboardProduct;
import com.tru2specs.android.objects.responses.dashboard.Type;
import com.tru2specs.android.productlisting.ProductListingActivityOld;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GP00471911 on 30-06-2017.
 */

public class DashboardCategoryListAdapter extends RecyclerView.Adapter<DashboardCategoryListAdapter.ViewHolder> {

    private Context mContext;
    private List<DashboardProduct> mCategories;
    private String categoryFor;

    public DashboardCategoryListAdapter(Context context, Type categoryType) {
        this.mContext = context;
        this.categoryFor=categoryType.getProductType();
        this.mCategories = categoryType.getTagCategory();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final DashboardProduct category = mCategories.get(i);
        viewHolder.mCategoryName.setText(category.getProdName());
        //TODO: category image needs to be render here
//        Glide.with(mContext).load(category.getUrl()).into(viewHolder.mCategoryImage);
        viewHolder.mRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, category.getCategoryName(), Toast.LENGTH_SHORT).show();
                navigateToProductListing(categoryFor,mCategories);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public void navigateToProductListing(String menWomenOrKid, List<DashboardProduct> mCategories) {

        Bundle bundle=new Bundle();
        bundle.putString(ProductListingActivityOld.KEY_CATEGORY_FOR,menWomenOrKid);
        bundle.putParcelableArrayList(ProductListingActivityOld.KEY_CATEGORIES_LIST, (ArrayList<? extends Parcelable>) mCategories);

        Intent productListingIntent=new Intent(mContext, ProductListingActivityOld.class);
        productListingIntent.putExtras(bundle);
        mContext.startActivity(productListingIntent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout mRow;
        public TextView mCategoryName;
        public ImageView mCategoryImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mRow = (RelativeLayout) itemView.findViewById(R.id.rl_dashboard_category_item_parent);
            mCategoryName = (TextView) itemView.findViewById(R.id.txt_category_name);
            mCategoryImage = (ImageView) itemView.findViewById(R.id.img_category_item);
        }
    }
}
