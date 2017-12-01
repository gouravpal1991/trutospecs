package com.tru2specs.android.productlisting;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superunitystudio.glasses.UnityPlayerActivity;
import com.tru2specs.android.R;
import com.tru2specs.android.productlisting.view.IProductListingView;
import com.unity3d.player.UnityPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ajaykumarsantra on 04/07/17.
 */

public class ProductListingActivityOld extends UnityPlayerActivity implements IProductListingView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static String KEY_CATEGORY_FOR = "category_for";
    public static String KEY_CATEGORIES_LIST = "category_list";
    public static Context mContext;
    @BindView(R.id.txt_product_listing_title)
    TextView mListingTitle;
    @BindView(R.id.txt_product_listing_tagline)
    TextView mTagLine;
//    @BindView(R.id.img_back)
//    ImageView mAppTitle;
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;
//    @BindView(R.id.img_store)
//    ImageView mStore;
    @BindView(R.id.img_back)
    ImageView mBack;
    @BindView(R.id.sc_3d_try_on)
    SwitchCompat m3dTrySwitch;
//    @BindView(R.id.rl_three_d_view)
    LinearLayout mTryOnView;

    private UnityPlayer mUnityPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_product_listing);
        mContext = this;
        ButterKnife.bind(this);
        mTryOnView = (LinearLayout) findViewById(R.id.rl_three_d_view);
        mTryOnView.setVisibility(View.GONE);
        setScreenDetails();


    }


    @Override
    public void setScreenDetails() {
        setToolbar();
        m3dTrySwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void navigateToProductDetails() {

    }

    @Override
    public void setToolbar() {
//        mStore.setVisibility(View.GONE);
////        mAppTitle.setVisibility(View.GONE);
//        mBack.setVisibility(View.VISIBLE);
//        mScreenTitle.setVisibility(View.VISIBLE);
//        mListingTitle.setVisibility(View.VISIBLE);
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
//        mScreenTitle.setText(getString(R.string.title_cart));
        mBack.setOnClickListener(this);
        mListingTitle.setText("men");
        mScreenTitle.setText("men");

    }

    @Override
    public void setProductsOnList() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.sc_3d_try_on:
                if(isChecked){

                    showThree3dView();
                    return;
                }
                mTryOnView.setVisibility(View.GONE);
                break;

        }
    }

    private void showThree3dView(){
        mUnityPlayer = new UnityPlayer(mContext);
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        boolean trueColor8888 = false;
        mUnityPlayer.init(glesMode, trueColor8888);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mTryOnView = (LinearLayout) findViewById(R.id.rl_three_d_view);
        mTryOnView.setVisibility(View.VISIBLE);
        mTryOnView.addView(mUnityPlayer.getView(),0,layoutParams);
    }
}
