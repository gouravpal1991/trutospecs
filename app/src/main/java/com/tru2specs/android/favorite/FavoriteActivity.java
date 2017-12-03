package com.tru2specs.android.favorite;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tru2specs.android.R;
import com.tru2specs.android.base.BaseActivity;
import com.tru2specs.android.favorite.adapter.FavoriteAdapter;
import com.tru2specs.android.favorite.listener.OnFavItemRemoveListener;
import com.tru2specs.android.favorite.presenter.FavoritePresenter;
import com.tru2specs.android.favorite.view.IFavoriteView;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.storage.database.DatabaseManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Selva
 */

public class FavoriteActivity extends BaseActivity implements IFavoriteView, OnFavItemRemoveListener {

    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;
    @BindView(R.id.img_back)
    ImageView mBack;
    @BindView(R.id.lin_no_item_in_cart)
    LinearLayout mNoFavItemLayout;
    @BindView(R.id.btn_continue_shopping)
    Button mContinueShopping;
    @BindView(R.id.rv_favorite)
    RecyclerView mFavList;
    @BindView(R.id.rl_progress)
    RelativeLayout mProgress;

    FavoritePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        setScreenTitle();
        hideNoItemInFavLayout();
        mPresenter = new FavoritePresenter(FavoriteActivity.this, this);
        mPresenter.attemptFetchFavItems();
    }

    @Override
    public void setScreenTitle() {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText(getString(R.string.title_favorite));
    }

    @Override
    public void showNoItemInFavLayout() {

        TextView textViewNoItem = (TextView) mNoFavItemLayout.findViewById(R.id.textview_no_item);
        textViewNoItem.setText(R.string.no_items_shortlisted);
        mNoFavItemLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoItemInFavLayout() {
        mNoFavItemLayout.setVisibility(View.GONE);
    }

    @Override
    public void setFavoriteItems(ArrayList<Product> products) {
        hideProgress();
        if (products != null && products.size() > 0) {
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mFavList.setLayoutManager(mLayoutManager);
            mFavList.setItemAnimator(new DefaultItemAnimator());

            FavoriteAdapter favAdapter = new FavoriteAdapter(FavoriteActivity.this, products, this);
            mFavList.setAdapter(favAdapter);
            favAdapter.notifyDataSetChanged();
            return;
        }
        showNoItemInFavLayout();
    }

    @Override
    public void onFailure(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(FavoriteActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        showNoItemInFavLayout();
    }

    @Override
    public void removeItemFromFavorites(String productId) {

    }

    @Override
    public void onRemove(String productId) {
        /*CartManager.getCartInstance(FavoriteActivity.this).deleteFavItem(productId);
        if (CartManager.getCartInstance(FavoriteActivity.this).getmFavItems().size() <= 0) {
            showNoItemInFavLayout();
        }*/

        DatabaseManager.getInstance(this).deleteFavourites(productId);
        if(DatabaseManager.getInstance(this).getFavouriteContactIds().size() <= 0) {
            showNoItemInFavLayout();
        }
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.btn_continue_shopping, R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_continue_shopping:
                finish();
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }

    @Override
    public void onNetworkChange(boolean isConnected) {

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_remove) {
          // clear all favorite items
        }
        return super.onOptionsItemSelected(item);

    }*/
}
