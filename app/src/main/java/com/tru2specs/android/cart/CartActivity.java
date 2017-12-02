package com.tru2specs.android.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tru2specs.android.R;
import com.tru2specs.android.address.adapter.SavedAddressesAdapter;
import com.tru2specs.android.base.BaseActivity;
import com.tru2specs.android.cart.adapter.CartAdapter;
import com.tru2specs.android.cart.listener.OnItemRemoveListener;
import com.tru2specs.android.cart.presenter.CartPresenter;
import com.tru2specs.android.cart.view.ICartView;
import com.tru2specs.android.checkout.CheckoutActivity;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.storage.database.DatabaseManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ajaykumarsantra on 18/07/17.
 */

public class CartActivity extends BaseActivity implements ICartView, OnItemRemoveListener {
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;
    @BindView(R.id.img_back)
    ImageView mBack;
    @BindView(R.id.lin_no_item_in_cart)
    LinearLayout mNoCartItemLayout;
    @BindView(R.id.btn_continue_shopping)
    Button mContinueShopping;
    @BindView(R.id.rv_cart)
    RecyclerView mCartList;

    @BindView(R.id.btn_proceed_to_checkout)
    Button mCheckout;
    @BindView(R.id.rl_progress)
    RelativeLayout mProgress;

    CartPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        init();

    }

    private void init() {
        setScreenTitle();
        hideNoItemInCartLayout();
        mPresenter = new CartPresenter(CartActivity.this, this);
        mPresenter.attemptFetchCartItems();
    }

    @OnClick({R.id.btn_continue_shopping, R.id.img_back, R.id.btn_proceed_to_checkout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_continue_shopping:
                finish();
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_proceed_to_checkout:
                navigateToCheckout();
                break;
        }
    }


    @Override
    public void onNetworkChange(boolean isConnected) {

    }

    @Override
    public void setScreenTitle() {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText(getString(R.string.title_cart));
    }

    @Override
    public void showNoItemInCartLayout() {
        mNoCartItemLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoItemInCartLayout() {
        mNoCartItemLayout.setVisibility(View.GONE);
    }

    @Override
    public void navigateToCheckout() {
        Intent checkoutIntent = new Intent(CartActivity.this, CheckoutActivity.class);
        startActivity(checkoutIntent);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCartItems(ArrayList<Product> products) {
        hideProgress();
        if (products != null && products.size() > 0) {
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mCartList.setLayoutManager(mLayoutManager);
            mCartList.setItemAnimator(new DefaultItemAnimator());


            CartAdapter cartAdapter = new CartAdapter(CartActivity.this, products, this);
            mCartList.setAdapter(cartAdapter);
            cartAdapter.notifyDataSetChanged();
            return;
        }
        showNoItemInCartLayout();
    }

    @Override
    public void onFailure(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(CartActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        showNoItemInCartLayout();
    }

    @Override
    public void removeItemFromCart(String productId) {

    }

    @Override
    public void onRemove(String productId) {

        /*CartManager.getCartInstance(CartActivity.this).deleteItemInCart(productId);
        if (CartManager.getCartInstance(CartActivity.this).getmCartItems().size() <= 0) {
            showNoItemInCartLayout();
        } */

        DatabaseManager.getInstance(this).deleteCartProduct(productId);
        if(DatabaseManager.getInstance(this).getCartProducts().size() <= 0) {
            showNoItemInCartLayout();
        }
    }
}
