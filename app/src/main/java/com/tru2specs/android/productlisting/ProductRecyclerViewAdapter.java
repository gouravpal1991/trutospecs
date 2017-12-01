package com.tru2specs.android.productlisting;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;


public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {
    private List<Products> productList;
    private Context context;

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
        Products product = productList.get(position);
        viewHolder.txt_product_name.setText(product.getProductName());
        viewHolder.txt_product_price.setText(context.getString(R.string.rupees) + " " + product.getPrice());

        Glide.with(context).load(product.getImageURL()).into(viewHolder.img_product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_product_name;
        private TextView txt_product_price;
        private ImageView img_product;

        public ViewHolder(View view) {
            super(view);

            txt_product_name = (TextView) view.findViewById(R.id.txt_product_name);
            txt_product_price = (TextView) view.findViewById(R.id.txt_product_price);
            img_product = (ImageView) view.findViewById(R.id.img_product);
        }
    }

}

