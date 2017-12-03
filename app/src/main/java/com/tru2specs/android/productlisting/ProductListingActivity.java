package com.tru2specs.android.productlisting;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.objects.responses.product.Data;
import com.tru2specs.android.objects.responses.product.Products;
import com.tru2specs.android.productlisting.view.IProductListingView;
import com.tru2specs.android.productslist.presenter.ProductListPresenter;
import com.tru2specs.android.productslist.view.IProductListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ajaykumarsantra on 04/07/17.
 */

public class ProductListingActivity extends AppCompatActivity implements IProductListingView,
        View.OnClickListener, OnCheckedChangeListener, IProductListView {

    public static String KEY_CATEGORY_FOR = "category_for";
    public static String KEY_CATEGORIES_LIST = "category_list";

    @BindView(R.id.txt_product_listing_title)
    TextView mListingTitle;
    @BindView(R.id.txt_product_listing_tagline)
    TextView mTagLine;

    //    @BindView(R.id.txt_pre_login_toolbar_title)
//    ImageView mAppTitle;
//    @BindView(R.id.txt_title_screen)
//    TextView mScreenTitle;
//    @BindView(R.id.img_store)
//    ImageView mStore;
    @BindView(R.id.img_back)
    ImageView mBack;
    @BindView(R.id.vp_product_list)
    ViewPager mProductListViewpager;
    @BindView(R.id.tl_product_list)
    TabLayout tabLayoutProductList;
    ProductListingViewPagerAdapter viewPagerAdapter;
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;

    @BindView(R.id.sc_3d_try_on)
    SwitchCompat m3dTrySwitch;
	@BindView(R.id.productImageLayout)
    LinearLayout mLinearLayoutProductImage;

	@BindView(R.id.rl_three_d_view)
	LinearLayout mThreeDLayout;
    private ProductListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);
        ButterKnife.bind(this);
        mPresenter = new ProductListPresenter(this, getApplicationContext());
        mPresenter.getProductData();
        setScreenDetails();
        init();
    }

    private void init() {
        setScreenTitle();
        m3dTrySwitch.setOnCheckedChangeListener(this);
    }

    private void setScreenTitle() {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText("MEN");

    }


    @Override
    public void setScreenDetails() {
        setToolbar();
    }

    @Override
    public void navigateToProductDetails() {

    }

    @Override
    public void setToolbar() {
//        mStore.setVisibility(View.GONE);
//        mAppTitle.setVisibility(View.GONE);
        mBack.setVisibility(View.VISIBLE);
//        mScreenTitle.setVisibility(View.VISIBLE);
        mListingTitle.setVisibility(View.VISIBLE);

        mBack.setOnClickListener(this);
        mListingTitle.setText("men");
//        mScreenTitle.setText("men");

    }

    @Override
    public void setProductsOnList() {

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
        switch (buttonView.getId()) {
            case R.id.sc_3d_try_on:
                if (isChecked) {
                    showThree3dView(true);
                    return;
                }
                else {
                    showThree3dView(false);
                }
//                mTryOnView.setVisibility(View.GONE);
                break;

        }
    }

    private void showThree3dView(boolean isShow) {
       
	    LinearLayout dLayout = (LinearLayout) findViewById(R.id.rl_three_d_view);

        if(isShow) {
            mLinearLayoutProductImage.setVisibility(View.GONE);

         /*   mUnityPlayer.requestFocus();
            int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
            boolean trueColor8888 = false;
            mUnityPlayer.init(glesMode, trueColor8888);
2
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            dLayout.setVisibility(View.VISIBLE);
            dLayout.addView(mUnityPlayer.getView(), 0, layoutParams);
*/
        }
        else {
            dLayout.setVisibility(View.GONE);
            mLinearLayoutProductImage.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void hideProgressView() {

    }

    @Override
    public void showProgressView() {

    }

    @Override
    public void setProductList(List<Products> productList) {
        viewPagerAdapter = new ProductListingViewPagerAdapter(getSupportFragmentManager(), productList);
        mProductListViewpager.setAdapter(viewPagerAdapter);
        tabLayoutProductList.setupWithViewPager(mProductListViewpager);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onSuccess(Data data) {

    }
}
