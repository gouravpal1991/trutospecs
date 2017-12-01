package com.tru2specs.android.menu.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.dashboard.model.DrawerMenu;
import com.tru2specs.android.productlisting.ProductListingActivity;
import com.tru2specs.android.productlisting.ProductListingActivityOld;

import java.util.List;

/**
 * Created by GP00471911 on 30-06-2017.
 */

public class DashboardMenuListAdapter extends RecyclerView.Adapter<DashboardMenuListAdapter.ViewHolder> {

    private Context mContext;
    private List<DrawerMenu> mMenuItems;
    private String categoryFor;

    public DashboardMenuListAdapter(Context context, List<DrawerMenu> menuItems) {
        this.mContext = context;
        this.mMenuItems = menuItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_menu_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
       final String menu = mMenuItems.get(i).getDrawerMenuItemName();
        viewHolder.mCategoryName.setText(menu);
        viewHolder.mCategoryName.setTextColor(mContext.getResources().getColor(R.color.secondary_color));
        //TODO: category image needs to be render here
//        Glide.with(mContext).load(category.getUrl()).into(viewHolder.mCategoryImage);
        viewHolder.mRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, category.getCategoryName(), Toast.LENGTH_SHORT).show();
                navigateToProductListing(menu);
//                viewHolder.mCategoryName.setTextColor(mContext.getResources().getColor(R.color.white));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMenuItems.size();
    }

    public void navigateToProductListing(String menu) {

        Bundle bundle=new Bundle();
        bundle.putString(ProductListingActivityOld.KEY_CATEGORY_FOR,menu);
//        bundle.putParcelableArrayList(ProductListingActivityOld.KEY_CATEGORIES_LIST, (ArrayList<? extends Parcelable>) mCategories);

        Intent productListingIntent=new Intent(mContext, ProductListingActivity.class);
        productListingIntent.putExtras(bundle);
        mContext.startActivity(productListingIntent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout mRow;
        public TextView mCategoryName;
//        public ImageView mCategoryImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mRow = (RelativeLayout) itemView.findViewById(R.id.rl_menu_item);
            mCategoryName = (TextView) itemView.findViewById(R.id.txt_menu_item);
//            mCategoryImage = (ImageView) itemView.findViewById(R.id.img_category_item);
        }
    }
}
