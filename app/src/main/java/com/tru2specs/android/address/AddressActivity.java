package com.tru2specs.android.address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tru2specs.android.R;
import com.tru2specs.android.address.presenter.AddressPresenter;
import com.tru2specs.android.address.view.IAddressView;
import com.tru2specs.android.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ajaykumarsantra on 15/08/17.
 */

public class AddressActivity extends BaseActivity implements IAddressView {
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;
    @BindView(R.id.img_back)
    ImageView mBack;
    @BindView(R.id.edt_address_name)
    EditText mName;
    @BindView(R.id.edt_address_line_1)
    EditText mLine1;
    @BindView(R.id.edt_address_line_2)
    EditText mLine2;
    @BindView(R.id.edt_landmark)
    EditText mLandmark;
    @BindView(R.id.edt_address_mobile)
    EditText mMobile;
    @BindView(R.id.edt_address_alternate_mobile)
    EditText mAltMobile;
    @BindView(R.id.edt_address_city)
    EditText mCity;
    @BindView(R.id.edt_address_state)
    EditText mState;
    @BindView(R.id.edt_address_zipcode)
    EditText mZip;
    @BindView(R.id.edt_address_country)
    EditText mCountry;
    @BindView(R.id.chk_address_mark_as_default)
    CheckBox mMarkDefault;
    @BindView(R.id.btn_address_save)
    Button mSave;
    @BindView(R.id.btn_address_saved_addresses)
    Button mSavedAddresses;
    @BindView(R.id.lin_signup_details)
    LinearLayout linSignupDetails;
    @BindView(R.id.rl_progress)
    RelativeLayout mProgress;
    private AddressPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        hideProgressView();
        setScreenTitle();
        mPresenter = new AddressPresenter(getApplicationContext(), this);
    }

    public void setScreenTitle() {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText(getString(R.string.title_add_address));
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
    public String getName() {
        return String.valueOf(mName.getText());
    }

    @Override
    public String getUserId() {
        //TODO:USERID needs to be extract from saved data in local storage
        return String.valueOf(mName.getText());
    }

    @Override
    public String getLine1() {
        return String.valueOf(mLine1.getText());
    }

    @Override
    public String getLine2() {
        return String.valueOf(mLine2.getText());
    }

    @Override
    public String getLine3() {
        return null;
    }

    @Override
    public String getLandmark() {
        return String.valueOf(mLandmark.getText());
    }

    @Override
    public String getState() {
        return String.valueOf(mState.getText());
    }

    @Override
    public String getCity() {
        return String.valueOf(mCity.getText());
    }

    @Override
    public String getPincode() {
        return String.valueOf(mZip.getText());
    }

    @Override
    public String getCountry() {
        return String.valueOf(mCountry.getText());
    }

    @Override
    public String getPhone1() {
        return String.valueOf(mMobile.getText());
    }

    @Override
    public String getPhone2() {
        return String.valueOf(mAltMobile.getText());
    }

    @Override
    public boolean isDefault() {
        return mMarkDefault.isChecked();
    }

    @Override
    public void navigateToMyAccount() {
        finish();
    }

    @Override
    public void navigateToSavedAddresses() {
        /*Intent intent = new Intent(AddressActivity.this,SavedAddressesActivity.class);
        startActivity(intent);*/
    }

    @Override
    public void onAddAddressFaliure(String msg) {
        Toast.makeText(AddressActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddAddressSuccess(String msg) {
        Toast.makeText(AddressActivity.this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onNetworkChange(boolean isConnected) {

    }

    @OnClick({R.id.btn_address_save, R.id.btn_address_saved_addresses, R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_address_save:
                mPresenter.attemptAddressSubmission();
                break;
            case R.id.btn_address_saved_addresses:
                navigateToSavedAddresses();
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }

}
