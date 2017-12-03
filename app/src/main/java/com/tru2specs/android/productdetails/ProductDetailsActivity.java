package com.tru2specs.android.productdetails;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.tru2specs.android.R;
import com.tru2specs.android.cart.CartActivity;
import com.tru2specs.android.objects.responses.productlisting.Data;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.objects.responses.useFor.Item;
import com.tru2specs.android.objects.responses.useFor.UseForResponse;
import com.tru2specs.android.productdetails.adapter.UseForAdapter;
import com.tru2specs.android.productdetails.presenter.ProductDetailsPresenter;
import com.tru2specs.android.productdetails.view.IProducDetailsView;
import com.tru2specs.android.storage.database.DatabaseManager;
import com.tru2specs.android.util.AppUtil;
import com.tru2specs.android.util.Constants;
import com.tru2specs.android.util.Helper;
import com.unity3d.player.UnityPlayerActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PB00471065 on 11/7/2017.
 */

public class ProductDetailsActivity extends UnityPlayerActivity implements IProducDetailsView {

    private static final String TAG = ProductDetailsActivity.class.getSimpleName();
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
    private ImageView mImageViewFavorite;

    @BindView(R.id.toggleSwitch3D)
    Switch mToggleSwitch3D;

    @BindView(R.id.productImageLayout)
    LinearLayout mProductImageLayout;

    @BindView(R.id.img_share)
    ImageView mImageViewShare;

    @BindView(R.id.three_d_view)
    LinearLayout mThreeDLayout;

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
        mImageViewFavorite = (ImageView) findViewById(R.id.img_fav);

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

        mImageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFavoriteList();
            }
        });

        mToggleSwitch3D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mToggleSwitch3D.isChecked()) {
                    show3DView(true);
                    mImageViewShare.setVisibility(View.VISIBLE);
                }
                else {
                    mImageViewShare.setVisibility(View.GONE);
                    show3DView(false);
                }
            }
        });

        mImageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeScreenShotAndShare();
            }
        });

        mPresenter = new ProductDetailsPresenter(ProductDetailsActivity.this, this);
        mPresenter.attemptGetProductDetails();

        AppUtil.getScreenHeight();
    }

    private void navigateToCheckout() {
        String productId = getProductId();
        if (!TextUtils.isEmpty(productId)) {
           /* CartManager cartManager = CartManager.getCartInstance(ProductDetailsActivity.this);
            if(!cartManager.getmCartItems().contains(productId)) {
                cartManager.addItemInCart(productId);
            }*/
           if(!DatabaseManager.getInstance(this).getCartProducts().contains(productId)) {
               DatabaseManager.getInstance(this).addCartProducts(productId);
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
           /* CartManager cartManager = CartManager.getCartInstance(ProductDetailsActivity.this);
            if(cartManager.getmCartItems().contains(productId)){
                Toast.makeText(ProductDetailsActivity.this, "Product already added to cart.", Toast.LENGTH_SHORT).show();
                return;
            }
            cartManager.addItemInCart(productId);
            Toast.makeText(ProductDetailsActivity.this, "Product added to cart.", Toast.LENGTH_SHORT).show();*/

           DatabaseManager databaseManager = DatabaseManager.getInstance(this);
            if(databaseManager.getCartProducts().contains(productId)) {
                Toast.makeText(ProductDetailsActivity.this, "Product already added to cart.", Toast.LENGTH_SHORT).show();
                return;
            }
            databaseManager.addCartProducts(productId);
            Toast.makeText(ProductDetailsActivity.this, "Product added to cart.", Toast.LENGTH_SHORT).show();
        }

    }
	
	 private void addToFavoriteList() {

        String productId = getProductId();
        if (!TextUtils.isEmpty(productId)) {
           /* CartManager cartManager = CartManager.getCartInstance(ProductDetailsActivity.this);
            if(cartManager.getmFavItems().contains(productId)){
                Toast.makeText(ProductDetailsActivity.this, "Product already shortlisted.", Toast.LENGTH_SHORT).show();
                return;
            }
            cartManager.addFavItem(productId);*/

            DatabaseManager databaseManager = DatabaseManager.getInstance(this);
            if(databaseManager.getFavouriteContactIds().contains(productId)) {
                Toast.makeText(ProductDetailsActivity.this, "Product already shortlisted.", Toast.LENGTH_SHORT).show();
                return;
            }
            databaseManager.addFavourites(productId);

            mImageViewFavorite.setImageResource(R.drawable.ic_fav_selected);
            Log.d(TAG, "shortlisted items count :" + databaseManager.getFavouriteContactIds().size());
            Toast.makeText(ProductDetailsActivity.this, "Product added to shortlisted.", Toast.LENGTH_SHORT).show();
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

       /* if(CartManager.getCartInstance(this).isFavProduct(getProductId())) {
            mImageViewFavorite.setImageResource(R.drawable.ic_fav_selected);
        }*/

       if(DatabaseManager.getInstance(this).getFavouriteContactIds().contains(getProductId())) {
           mImageViewFavorite.setImageResource(R.drawable.ic_fav_selected);
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
            Log.d(TAG, "ProductId : " + productId);
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


    private void show3DView(boolean isShow) {

        LinearLayout dLayout = (LinearLayout) findViewById(R.id.three_d_view);

        if(isShow) {
            DisplayMetrics displaymetrics = Resources.getSystem().getDisplayMetrics();
            int windowHeight = displaymetrics.heightPixels;

            Log.d(TAG, "windowHeight : " +  windowHeight);

            mProductImageLayout.setVisibility(View.GONE);

            mUnityPlayer.requestFocus();
            int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
            boolean trueColor8888 = false;
            mUnityPlayer.init(glesMode, trueColor8888);

            ViewGroup.LayoutParams layoutParams = dLayout.getLayoutParams();
            layoutParams.height = windowHeight - windowHeight/3;

            dLayout.setVisibility(View.VISIBLE);
            dLayout.addView(mUnityPlayer.getView(), 0, layoutParams);

        }
        else {
            dLayout.setVisibility(View.GONE);
            dLayout.removeView(mUnityPlayer.getView());
            mProductImageLayout.setVisibility(View.VISIBLE);

        }
    }

    private void takeScreenShotAndShare() {

        Toast.makeText(this, "Taking Picture.", Toast.LENGTH_LONG).show();

        File fileDir = AppUtil.getMainDirectoryName(this);

        String screenshotFile = fileDir.getAbsolutePath() + "/test.png";
        Log.d(TAG, "Screenshot Path :" + screenshotFile);
        //Toast.makeText(this, "Screenshot : "  , Toast.LENGTH_SHORT).show();

        mUnityPlayer.UnitySendMessage("FaceDisplay", "TakeScreenshot", screenshotFile); // path .png format
        //  mUnityPlayer.UnitySendMessage("FaceDisplay","TakeScreenshot",null);

        try {
            Thread.sleep(2000); // wait 2 sec for unity image cpature
        }catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File(screenshotFile);

        if(file.exists()) {
            shareScreenshot(file);// share screenshot
        }
        else {
            Log.d(TAG, "Screenshot file not available in memory");
        }
    }

    /*private void takeScreenShotAndShare() {
        // Take screen shot
        Bitmap bitmap = AppUtil.getScreenShot(mThreeDLayout);

        if(bitmap != null) {
            File saveFile = AppUtil.getMainDirectoryName(this);//get the path to save screenshot
            File file = AppUtil.store(bitmap, "screenshot.jpg", saveFile);//save the screenshot to selected path
            shareScreenshot(file);// share screenshot
        }

    }*/

    /*  Share Screenshot  */
    private void shareScreenshot(File file) {
        Toast.makeText(this, "Share Picture.", Toast.LENGTH_SHORT).show();

        Uri uri = Uri.fromFile(file);//Convert file path into Uri for sharing
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Tru2Spec 3D Try");
       // intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.sharing_text));
        intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
        startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }

}
