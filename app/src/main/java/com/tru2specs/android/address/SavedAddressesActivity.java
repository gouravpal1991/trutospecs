package com.tru2specs.android.address;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.tru2specs.android.objects.responses.savedaddresses.Address;
import com.tru2specs.android.objects.responses.savedaddresses.Data;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ajaykumarsantra on 15/08/17.
 */

public class SavedAddressesActivity extends BaseActivity implements ISavedAddressesView, OnUpdateAddressListener {
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;
    @BindView(R.id.img_back)
    ImageView mBack;

    @BindView(R.id.rl_progress)
    RelativeLayout mProgress;
    @BindView(R.id.rv_addresses)
    RecyclerView mAddresses;
    private SavedAddressesPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresses);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        hideProgressView();
        setScreenTitle();


        mPresenter = new SavedAddressesPresenter(getApplicationContext(), this);
        getAddresses();
        // Type listType = new TypeToken<List<String>>() {}.getType();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAddresses.setLayoutManager(mLayoutManager);
        mAddresses.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
    }

    public void setScreenTitle() {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText(getString(R.string.saved_addresses));
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
        Toast.makeText(SavedAddressesActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetAddressesSuccess(Data data) {
        List<Address> addresses = data.getAddress();
        SavedAddressesAdapter addressesAdapter = new SavedAddressesAdapter(getApplicationContext(), addresses, this);
        mAddresses.setAdapter(addressesAdapter);
        addressesAdapter.notifyDataSetChanged();
        hideProgressView();
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
