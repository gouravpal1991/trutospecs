package com.tru2specs.android.favorite.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tru2specs.android.R;
import com.tru2specs.android.favorite.listener.OnFavItemRemoveListener;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.storage.database.DatabaseManager;
import com.tru2specs.android.util.Constants;

import java.util.ArrayList;

/**
 * Created by Selva.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Product> mProducts = new ArrayList<>();
    Context mContext;
    OnFavItemRemoveListener mRemoveListener;

    public FavoriteAdapter(Context context, ArrayList<Product> products, OnFavItemRemoveListener removeListener) {
        mProducts = products;
        mContext = context;
        mRemoveListener = removeListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fav_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (mProducts.size() > 0) {
            Product product = mProducts.get(position);
            setImage(((ProductViewHolder) holder).mItemImage, product.getImageURL());
            ((ProductViewHolder) holder).mName.setText(product.getProductName());

            if (!TextUtils.isEmpty(String.valueOf(product.getDiscount()))) {
                if (!TextUtils.isEmpty(String.valueOf(product.getMRP())))
                    ((ProductViewHolder) holder).mActualAmt.setText(String.valueOf(Constants.CURRENCY + product.getMRP()));
                if (!TextUtils.isEmpty(String.valueOf(product.getMRPAfterDiscount())))
                    ((ProductViewHolder) holder).mDiscAmt.setText(String.valueOf(Constants.CURRENCY + product.getMRPAfterDiscount()));

            }
        }
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    @Override
    public int getItemViewType(int position) {
       return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View View;
        public ImageView mItemImage;
        public TextView mName;
        public TextView mDiscAmt;
        public TextView mActualAmt;
        public Button mRemoveFromFav;
        public Button mAddToCart;

        public ProductViewHolder(View v) {
            super(v);
            View = v;
            mItemImage = (ImageView) v.findViewById(R.id.img_fav_item);
            mName = (TextView) v.findViewById(R.id.txt_item_name);
            mActualAmt = (TextView) v.findViewById(R.id.txt_actual_amount);
            mDiscAmt = (TextView) v.findViewById(R.id.txt_discounted_amount);
            mActualAmt.setPaintFlags(mActualAmt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            mRemoveFromFav = (Button) v.findViewById(R.id.btn_remove_from_fav);
            mAddToCart = (Button) v.findViewById(R.id.btn_add_to_cart);

            mRemoveFromFav.setOnClickListener(this);
            mAddToCart.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_to_cart:
                    addToCart(getAdapterPosition());
                    break;
                case R.id.btn_remove_from_fav:
                    removeAt(getAdapterPosition());
                    break;
            }
        }
    }

    private void addToCart(int position) {

        String productId = String.valueOf(mProducts.get(position).getProductId());
        if (!TextUtils.isEmpty(productId)) {
            /*CartManager cartManager = CartManager.getCartInstance(mContext);
            if(cartManager.getmCartItems().contains(productId)){
                Toast.makeText(mContext, "Product already added to cart.", Toast.LENGTH_SHORT).show();
                return;
            }
            cartManager.addItemInCart(productId);
            Toast.makeText(mContext, "Product added to cart.", Toast.LENGTH_SHORT).show();
            */
            DatabaseManager databaseManager = DatabaseManager.getInstance(mContext);
            if(databaseManager.getCartProducts().contains(productId)) {
                Toast.makeText(mContext, "Product already added to cart.", Toast.LENGTH_SHORT).show();
                return;
            }
            databaseManager.addCartProducts(productId);
            Toast.makeText(mContext, "Product added to cart.", Toast.LENGTH_SHORT).show();
        }

    }

    private void removeAt(int adapterPosition) {
        mRemoveListener.onRemove(String.valueOf(mProducts.get(adapterPosition).getProductId()));
        mProducts.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        notifyItemRangeChanged(adapterPosition, mProducts.size());
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
