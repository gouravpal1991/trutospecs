package com.tru2specs.android.checkout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.base.BaseActivity;
import com.tru2specs.android.checkout.adapter.CheckoutPagerAdapter;
import com.tru2specs.android.checkout.fragment.CheckoutAddressFragment;
import com.tru2specs.android.checkout.fragment.CheckoutPaymentFragment;
import com.tru2specs.android.checkout.fragment.CheckoutThankyouFragment;
import com.tru2specs.android.checkout.view.ICheckoutView;
import com.tru2specs.android.customview.CustomViewPager;
import com.tru2specs.android.objects.responses.savedaddresses.Address;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by palgour on 8/25/17.
 */

public class CheckoutActivity extends BaseActivity implements ICheckoutView, TabLayout.OnTabSelectedListener {
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;
    @BindView(R.id.vp_checkout)
    CustomViewPager mCheckoutPager;
    @BindView(R.id.rl_progress)
    RelativeLayout mProgress;

    //    @BindView(R.id.tabs)
    TabLayout mTabs;
    private Address mSelectedAddress = null;
    @BindView(R.id.img_back)
    ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
    }

    @Override
    public void onNetworkChange(boolean isConnected) {

    }

    @Override
    public void showProgressView() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressView() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void setScreenTitle() {
            mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            mScreenTitle.setText(getString(R.string.title_checkout));

    }

    @Override
    public void init() {
        setScreenTitle();
        mTabs = (TabLayout) findViewById(R.id.tabs);

        mCheckoutPager.setAdapter(new CheckoutPagerAdapter(getSupportFragmentManager(), getFragments()));
        mCheckoutPager.setPagingEnabled(false);

        mTabs.setupWithViewPager(mCheckoutPager);
        createCustomTabs();
        mTabs.addOnTabSelectedListener(this);


    }

    @Override
    public List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(CheckoutAddressFragment.newInstance("Checkout_Address_Fragment"));
        fragmentList.add(CheckoutPaymentFragment.newInstance("Checkout_Address_Fragment"));
        fragmentList.add(CheckoutThankyouFragment.newInstance("Checkout_Address_Fragment"));
        return fragmentList;
    }

    @Override
    public void createCustomTabs() {
        LinearLayout tabOne = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.selected_custom_tab, null);
        mTabs.getTabAt(0).setCustomView(tabOne);

        LinearLayout tabTwo = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.selected_custom_tab, null);
        TextView textView = (TextView) tabTwo.findViewById(R.id.txt_checkout_tab_title);
        TextView txtIndicator = (TextView) tabTwo.findViewById(R.id.tab_indicator);
        txtIndicator.setBackground(getResources().getDrawable(R.drawable.light_circle));
        textView.setText("Payment");
        mTabs.getTabAt(1).setCustomView(tabTwo);

        LinearLayout tabThree = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.selected_custom_tab, null);
        TextView tabThreeText = (TextView) tabThree.findViewById(R.id.txt_checkout_tab_title);
        tabThreeText.setText("Complete");
        TextView txtIndicator3 = (TextView) tabThree.findViewById(R.id.tab_indicator);
        txtIndicator3.setBackground(getResources().getDrawable(R.drawable.light_circle));
        mTabs.getTabAt(2).setCustomView(tabThree);

    }

    @Override
    public void setAddress(Address address) {
        mSelectedAddress = address;
    }

    @Override
    public Address getAddress() {
        return mSelectedAddress;
    }

    @Override
    public void goToNextPagerSlide() {
        int position = mCheckoutPager.getCurrentItem();
        mCheckoutPager.setCurrentItem(position + 1);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
//        Toast.makeText(CheckoutActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();

        //if (position > 0) {
        View tabView = tab.getCustomView();
        TextView txtIndicator = (TextView) tabView.findViewById(R.id.tab_indicator);
        TextView title = (TextView) tabView.findViewById(R.id.txt_checkout_tab_title);
        title.setTextColor(getResources().getColor(R.color.button_start_color));
        txtIndicator.setBackground(getResources().getDrawable(R.drawable.dark_circle));
        //  return;
//            textView.setText("Payment");
        //}


    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
//        int position = tab.getPosition();
//        if (position!=0 || position!=1) {
//            View tabView = tab.getCustomView();
//            TextView txtIndicator = (TextView) tabView.findViewById(R.id.tab_indicator);
//            txtIndicator.setBackground(getResources().getDrawable(R.drawable.light_circle));
//        }

        View tabView = tab.getCustomView();
        TextView txtIndicator = (TextView) tabView.findViewById(R.id.tab_indicator);

        TextView title = (TextView) tabView.findViewById(R.id.txt_checkout_tab_title);
        title.setTextColor(getResources().getColor(R.color.black));
        txtIndicator.setBackground(getResources().getDrawable(R.drawable.light_circle));

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
