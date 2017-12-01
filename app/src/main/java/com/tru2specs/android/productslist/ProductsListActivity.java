package com.tru2specs.android.productslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.tru2specs.android.R;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import com.tru2specs.android.util.Helper;

import butterknife.BindView;

/**
 * Created by GP00471911 on 03-02-2017.
 */

public class ProductsListActivity extends AppCompatActivity {
//    private ProductResponse productResponse;
//    private List<Product> productList = new ArrayList<>();

    TextView mScreenTitle;
    //TODO:use below link to implement lazy loading
//    https://codentrick.com/load-more-recyclerview-bottom-progressbar/
//    http://findnerd.com/list/view/Lazy-loading-in-android-RecyclerView-/11720/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        mScreenTitle = (TextView) findViewById(R.id.txt_pre_login_toolbar_title);
        setScreenDetails();

//        productResponse = new Gson().fromJson(Helper.loadJSONFromAsset(ProductsListActivity.this, "products.json"), ProductResponse.class);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_products_list);
//        productList = productResponse.getProducts();
//        ProductListAdapter mAdapter = new ProductListAdapter(ProductsListActivity.this, productList);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);

    }

    public void setScreenDetails(){
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText(getString(R.string.title_checkout));
    }
}