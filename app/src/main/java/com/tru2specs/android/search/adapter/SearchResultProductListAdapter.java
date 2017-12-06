package com.tru2specs.android.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tru2specs.android.R;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.productdetails.ProductDetailsActivity;
import com.tru2specs.android.util.Constants;

import java.util.List;

/**
 * Created by Selva
 */

public class SearchResultProductListAdapter extends RecyclerView.Adapter<SearchResultProductListAdapter.ProductsViewHolder> {

    private static final String TAG = SearchResultProductListAdapter.class.getName();
    private final Context mContext;
    private final List<Product> mList;

    public SearchResultProductListAdapter(Context context, List<Product> productList) {
        this.mContext = context;
        this.mList = productList;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_search, parent, false);
        return new ProductsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        Product product = mList.get(position);
        setImage(((ProductsViewHolder) holder).productImage, product.getImageURL());

        ((ProductsViewHolder) holder).productName.setText(product.getProductName());
        if (!TextUtils.isEmpty(String.valueOf(product.getDiscount()))) {
            int discountAmt = product.getDiscount();
            int mrp = product.getMRP();

            if (!TextUtils.isEmpty(String.valueOf(product.getMRP())))
                ((ProductsViewHolder) holder).productAmount.setText(String.valueOf(Constants.CURRENCY + product.getMRP()));
            if (!TextUtils.isEmpty(String.valueOf(product.getMRPAfterDiscount())))
                ((ProductsViewHolder) holder).productDiscountAmount.setText(String.valueOf(Constants.CURRENCY + product.getMRPAfterDiscount()));
        }
    }

    private void setImage(ImageView image, String url) {
        Glide.with(mContext).load(url).crossFade().error(R.drawable.no_image).fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productName;
        TextView productDiscountAmount;
        TextView productAmount;
        ImageView productImage;

        public ProductsViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.txt_product_name);
            productImage = (ImageView) itemView.findViewById(R.id.image_product);
            productAmount = (TextView) itemView.findViewById(R.id.txt_actual_amount);
            productDiscountAmount = (TextView) itemView.findViewById(R.id.txt_discounted_amount);
            productAmount.setPaintFlags(productAmount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            String productId = Integer.toString(mList.get(getAdapterPosition()).getProductId());
            Log.d(TAG, "Onclick - Search Result Product Item :: productId : " + productId);

            // navigate to product details
            Intent intent = new Intent(mContext, ProductDetailsActivity.class);
            intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_ID, productId);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }
}
