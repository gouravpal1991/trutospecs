package com.tru2specs.android.productdetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.tru2specs.android.R;
import com.tru2specs.android.address.presenter.AddressPresenter;
import com.tru2specs.android.cart.CartActivity;
import com.tru2specs.android.checkout.CheckoutActivity;
import com.tru2specs.android.dashboard.DashboardActivity;
import com.tru2specs.android.manager.CartManager;
import com.tru2specs.android.objects.request.ProudctListingRequest;
import com.tru2specs.android.objects.responses.productlisting.Data;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.objects.responses.productlisting.ProductListingResponse;
import com.tru2specs.android.objects.responses.useFor.Item;
import com.tru2specs.android.objects.responses.useFor.UseForResponse;
import com.tru2specs.android.productdetails.adapter.UseForAdapter;
import com.tru2specs.android.productdetails.presenter.ProductDetailsPresenter;
import com.tru2specs.android.productdetails.view.IProducDetailsView;
import com.tru2specs.android.productlisting.view.IProductListingView;
import com.tru2specs.android.util.Constants;
import com.tru2specs.android.util.Helper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PB00471065 on 11/7/2017.
 */

public class ProductDetailsActivity extends AppCompatActivity implements IProducDetailsView {

    public static final String KEY_PRODUCT_ID = "product_id";
    @BindView(R.id.txt_actual_amount)
    TextView mTxtActualAmount;
    @BindView(R.id.image_viewpager)
    ViewPager mImageViewpager;
    @BindView(R.id.tab_indicator)
    TabLayout mIndicator;
    private CustomPagerAdapter mPageAdapter;
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;
    @BindView(R.id.btn_buy_now)
    Button mBuyNow;
    @BindView(R.id.img_back)
    ImageView mBack;
    private Bundle mBundle;
    //    @BindView(R.id.txt_product_details_item_name)
    private TextView mProductName;
    private ProductDetailsPresenter mPresenter;
    private TextView mProductDesc;
    private TextView mMrpAfterDiscount;
    private TextView mMrp;
    private TextView mOff;
    private ArrayList<String> mImages;
    private Button mAddToCart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        mProductName = (TextView) findViewById(R.id.txt_product_details_item_name);
        mProductDesc = (TextView) findViewById(R.id.txt_product_details_product_desc);
        mMrpAfterDiscount = (TextView) findViewById(R.id.txt_discounted_amount);
        mMrp = (TextView) findViewById(R.id.txt_actual_amount);
        mOff = (TextView) findViewById(R.id.txt_offer);
        mAddToCart = (Button) findViewById(R.id.btn_add_to_cart);

        //for line over text
        mTxtActualAmount.setPaintFlags(mTxtActualAmount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        setScreenTitle();
        mBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCheckout();
            }
        });
        mAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCart();
            }
        });
        mPresenter = new ProductDetailsPresenter(ProductDetailsActivity.this, this);
        mPresenter.attemptGetProductDetails();
    }

    private void navigateToCheckout() {
        String productId = getProductId();
        if (!TextUtils.isEmpty(productId)) {
            CartManager cartManager = CartManager.getCartInstance(ProductDetailsActivity.this);
            if(!cartManager.getmCartItems().contains(productId)) {
                cartManager.addItemInCart(productId);
            }
        }
        Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
        startActivity(intent);
    }

    private void navigateToCart() {
//        Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
//        startActivity(intent);
        String productId = getProductId();
        if (!TextUtils.isEmpty(productId)) {
            CartManager cartManager = CartManager.getCartInstance(ProductDetailsActivity.this);
            if(cartManager.getmCartItems().contains(productId)){
                Toast.makeText(ProductDetailsActivity.this, "Product already added to cart.", Toast.LENGTH_SHORT).show();
                return;
            }
            cartManager.addItemInCart(productId);
            Toast.makeText(ProductDetailsActivity.this, "Product added to cart.", Toast.LENGTH_SHORT).show();
        }

    }

    private void chooseUseFor(){
        UseForResponse useForResponse = new Gson().fromJson(Helper.loadJSONFromAsset(ProductDetailsActivity.this, "products.json"), UseForResponse.class);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_products_list);
        List<Item> items = useForResponse.getData().getItems();
        UseForAdapter mAdapter = new UseForAdapter(ProductDetailsActivity.this, items);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void setScreenTitle(String productId) {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText(productId);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setToolbar() {

    }


    @Override
    public void setProductDetails(Product product) {

        mImages = new ArrayList<>();
        //TODO: convert getImageURL for list from string
        if (!TextUtils.isEmpty(product.getImageURL())) {
            mImages.add(product.getImageURL());

            mPageAdapter = new CustomPagerAdapter(this);
            mImageViewpager.setAdapter(mPageAdapter);
            mIndicator.setupWithViewPager(mImageViewpager, true);
        }

        if (!TextUtils.isEmpty(product.getProductName()))
            mProductName.setText(product.getProductName());

        if (!TextUtils.isEmpty(product.getDescription()))
            mProductDesc.setText(product.getDescription());

        if (!TextUtils.isEmpty(String.valueOf(product.getMRPAfterDiscount())))
            mMrpAfterDiscount.setText(String.valueOf(Constants.CURRENCY + product.getMRPAfterDiscount()));


        if (!TextUtils.isEmpty(String.valueOf(product.getDiscount()))) {
            int discountAmt = product.getDiscount();
            int mrp = product.getMRP();
            int percent = (discountAmt * 100) / mrp;

            mOff.setText(String.valueOf(percent + Constants.PERCENCT_OFF));
            if (!TextUtils.isEmpty(String.valueOf(product.getMRP())))
                mMrp.setText(String.valueOf(Constants.CURRENCY + product.getMRP()));
        }
    }

    private void setImage(ImageView image, String url) {
        Glide.with(ProductDetailsActivity.this).load(url)
                .crossFade()
                .error(R.drawable.no_image)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(ProductDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Data response) {
        if (response != null && response.getProducts().size() > 0) {
            setProductDetails(response.getProducts().get(0));
            return;
        }
        Toast.makeText(ProductDetailsActivity.this, "Product not found.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void getProductDetails(Product product) {

    }


    @Override
    public String getProductId() {
        mBundle = getIntent().getExtras();
        if (mBundle != null && mBundle.containsKey(KEY_PRODUCT_ID)) {
            String productId = mBundle.getString(KEY_PRODUCT_ID);
            setScreenTitle(productId);
            return productId;
        }
        return "";
    }


    class CustomPagerAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;
        private int mResources[] = {
                R.drawable.bear,
                R.drawable.see_dexter,
                R.drawable.tryon_3d_banner
        };

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.product_detail_pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            setImage(imageView, mImages.get(position));
            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

}
