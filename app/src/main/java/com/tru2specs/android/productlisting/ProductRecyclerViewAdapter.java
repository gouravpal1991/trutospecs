package com.tru2specs.android.productlisting;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tru2specs.android.R;
import com.tru2specs.android.objects.responses.product.Products;
import com.tru2specs.android.productdetails.ProductDetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private List<Products> productList;
    private Context context;
    private Products product;

    public ProductRecyclerViewAdapter(Context context, List<Products> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ProductRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        product = productList.get(position);
        viewHolder.txt_product_name.setText(product.getProductName());
        viewHolder.txt_product_price.setText(context.getString(R.string.rupees) + " " + product.getPrice());
        viewHolder.txt_product_name.setOnClickListener(this);
        viewHolder.img_product.setOnClickListener(this);
        Glide.with(context).load(product.getImageURL()).into(viewHolder.img_product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_dashboard_horizontal_item_parent:
            case R.id.img_product:
            case R.id.txt_product_name:
                navigateToProductDetails(String.valueOf(product.getProductId()));
                break;
        }
    }

    private void navigateToProductDetails(String prodId) {

        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_ID, prodId);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_product_name;
        private TextView txt_product_price;
        private ImageView img_product;
        private ImageView imageViewThreeD;
        private ImageView imageViewFavorite;

        public ViewHolder(View view) {
            super(view);

            txt_product_name = (TextView) view.findViewById(R.id.txt_product_name);
            txt_product_price = (TextView) view.findViewById(R.id.txt_product_price);
            img_product = (ImageView) view.findViewById(R.id.img_product);
            imageViewThreeD = (ImageView) view.findViewById(R.id.threeDImage);
            imageViewFavorite = (ImageView) view.findViewById(R.id.favImage);

            imageViewFavorite.setOnClickListener(this);
            imageViewThreeD.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.threeDImage:
                    showThree3dView();
                    break;
                case R.id.favImage:
                    break;
            }
        }
    }

    private void showThree3dView() {

    }

}

