package com.tru2specs.android.cart.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tru2specs.android.R;
import com.tru2specs.android.cart.listener.OnItemRemoveListener;
import com.tru2specs.android.dashboard.DashboardActivity;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.util.Constants;

import java.util.ArrayList;

/**
 * Created by palgour on 8/25/17.
 */

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_PRODUCT = 0;
    ArrayList<Product> mProducts = new ArrayList<>();
    Context mContext;
    OnItemRemoveListener mRemoveListener;

    public CartAdapter(Context context, ArrayList<Product> products, OnItemRemoveListener removeListener) {
        mProducts = products;
        mContext = context;
        mRemoveListener = removeListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_price_details, parent, false);
            return new FooterViewHolder(view);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProductViewHolder) {
            if (mProducts.size() > 0) {
                Product product = mProducts.get(position);
                setImage(((ProductViewHolder) holder).mItemImage, product.getImageURL());

                ((ProductViewHolder) holder).mName.setText(product.getProductName());
                if (!TextUtils.isEmpty(String.valueOf(product.getDiscount()))) {
                    int discountAmt = product.getDiscount();
                    int mrp = product.getMRP();
                    int percent = (discountAmt * 100) / mrp;

//                mOff.setText(String.valueOf(percent + Constants.PERCENCT_OFF));
                    if (!TextUtils.isEmpty(String.valueOf(product.getMRP())))
                        ((ProductViewHolder) holder).mActualAmt.setText(String.valueOf(Constants.CURRENCY + product.getMRP()));
                    if (!TextUtils.isEmpty(String.valueOf(product.getMRPAfterDiscount())))
                        ((ProductViewHolder) holder).mDiscAmt.setText(String.valueOf(Constants.CURRENCY + product.getMRPAfterDiscount()));


                }
                return;
            }
        }

        //implement for footerviewholder
        //holder instancOf FooterViewHolder
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_PRODUCT;
    }

    private boolean isPositionFooter(int position) {
        return position > mProducts.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View View;
        public ImageView mItemImage;
        public TextView mName;
        public TextView mDiscAmt;
        public TextView mActualAmt;
        public TextView mIncrementCount;
        public TextView mDecrementCount;
        public TextView mRemoveFromCart;

        public ProductViewHolder(View v) {
            super(v);
            View = v;
            mItemImage = (ImageView) v.findViewById(R.id.img_cart_item);
            mName = (TextView) v.findViewById(R.id.txt_item_name);
            mActualAmt = (TextView) v.findViewById(R.id.txt_actual_amount);
            mDiscAmt = (TextView) v.findViewById(R.id.txt_discounted_amount);
            mActualAmt.setPaintFlags(mActualAmt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mIncrementCount = (Button) v.findViewById(R.id.btn_add_to_cart);

            mRemoveFromCart = (Button) v.findViewById(R.id.btn_remove_from_cart);
            mRemoveFromCart.setOnClickListener(this);
            // Add your UI Components here
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_to_cart:
                    break;
                case R.id.btn_remove_from_cart:
                    removeAt(getAdapterPosition());
                    break;
            }
        }
    }

    private void removeAt(int adapterPosition) {
        mRemoveListener.onRemove(String.valueOf(mProducts.get(adapterPosition).getProductId()));
        mProducts.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        notifyItemRangeChanged(adapterPosition, mProducts.size());

    }


    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public View View;

        public FooterViewHolder(View v) {
            super(v);
            View = v;
            // Add your UI Components here
        }

    }

    private void setImage(ImageView image, String url) {
        Glide.with(mContext).load(url)
                .crossFade()
                .error(R.drawable.no_image)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);
    }
}
