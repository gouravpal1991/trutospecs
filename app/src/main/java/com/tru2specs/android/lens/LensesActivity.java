package com.tru2specs.android.lens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tru2specs.android.R;
import com.tru2specs.android.address.adapter.SavedAddressesAdapter;
import com.tru2specs.android.address.listener.OnUpdateAddressListener;
import com.tru2specs.android.address.presenter.SavedAddressesPresenter;
import com.tru2specs.android.address.view.ISavedAddressesView;
import com.tru2specs.android.base.BaseActivity;
import com.tru2specs.android.cart.CartActivity;
import com.tru2specs.android.lens.adapter.LensesAdapter;
import com.tru2specs.android.lens.presenter.LensPresenter;
import com.tru2specs.android.lens.view.ILensesView;
import com.tru2specs.android.objects.responses.lens.Lenses;
import com.tru2specs.android.objects.responses.savedaddresses.Address;
import com.tru2specs.android.objects.responses.savedaddresses.Data;
import com.tru2specs.android.productdetails.ProductDetailsActivity;
import com.tru2specs.android.storage.database.DatabaseManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ajaykumarsantra on 15/08/17.
 */

public class LensesActivity extends BaseActivity implements ILensesView, OnUpdateAddressListener {
    public static final String KEY_PRODUCT_ID = "productId";
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;
    @BindView(R.id.img_back)
    ImageView mBack;

    @BindView(R.id.rl_progress)
    RelativeLayout mProgress;
    @BindView(R.id.rv_lenses)
    RecyclerView mAddresses;
    private LensPresenter mPresenter;
    private Bundle mBundle;
    private String mProductId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lenses);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        hideProgressView();
        setScreenTitle();


        mPresenter = new LensPresenter(getApplicationContext(), this);
        getAddresses();
        // Type listType = new TypeToken<List<String>>() {}.getType();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAddresses.setLayoutManager(mLayoutManager);
        mAddresses.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
        mBundle = getIntent().getExtras();
        if(mBundle!=null && mBundle.containsKey(KEY_PRODUCT_ID)){
            mProductId = mBundle.getString(KEY_PRODUCT_ID);
        }
    }

    public void setScreenTitle() {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText(getString(R.string.select_lens));
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public void hideProgressView() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showProgressView() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetAddressesFailure(String msg) {
        Toast.makeText(LensesActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetLensSuccess(com.tru2specs.android.objects.responses.lens.Data data) {
        List<Lenses> lenses = data.getLenses();
        LensesAdapter lensesAdapter = new LensesAdapter(getApplicationContext(), lenses, this);
        mAddresses.setAdapter(lensesAdapter);
        lensesAdapter.notifyDataSetChanged();
        hideProgressView();
    }

    @Override
    public void addProductInCart() {
        if (!TextUtils.isEmpty(mProductId)) {
            if(!DatabaseManager.getInstance(this).getCartProducts().contains(mProductId)) {
                DatabaseManager.getInstance(this).addCartProducts(mProductId);
            }
        }
        Intent intent = new Intent(LensesActivity.this, CartActivity.class);
        startActivity(intent);
    }


    @Override
    public void getAddresses() {
        mPresenter.getSavedAddresses();
    }


    @Override
    public void onNetworkChange(boolean isConnected) {

    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void updateAddress(Address address) {
        mPresenter.updateAddress(address);
    }
}
