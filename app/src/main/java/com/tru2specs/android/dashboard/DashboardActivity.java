package com.tru2specs.android.dashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tru2specs.android.R;
import com.tru2specs.android.base.BaseActivity;
import com.tru2specs.android.basicinfo.BasicInfoActivity;
import com.tru2specs.android.cart.CartActivity;
import com.tru2specs.android.customview.custompager.InfiniteViewPager;
import com.tru2specs.android.dashboard.adapter.DashboardCategoryPagerAdapter;
import com.tru2specs.android.dashboard.adapter.DashboardOffersPagerAdapter;
import com.tru2specs.android.dashboard.adapter.DashboardVerticalRVAdapter;
import com.tru2specs.android.dashboard.presenter.DashboardPresenter;
import com.tru2specs.android.dashboard.view.IDashboardView;
import com.tru2specs.android.login.LoginActivity;
import com.tru2specs.android.manager.SessionManager;
import com.tru2specs.android.menu.DashboardMenuPagerAdapter;
import com.tru2specs.android.objects.responses.dashboard.DashboardResponse;
import com.tru2specs.android.objects.responses.dashboard.OfferHeader;
import com.tru2specs.android.objects.responses.dashboard.Type;
import com.tru2specs.android.objects.responses.loginSignup.Cart;
import com.tru2specs.android.objects.responses.loginSignup.Data;
import com.tru2specs.android.objects.responses.loginSignup.User;
import com.tru2specs.android.productslist.ProductsListActivity;
import com.tru2specs.android.profile.ProfileActivity;
import com.tru2specs.android.search.SearchActivity;
import com.tru2specs.android.signup.SignUpActivity;
import com.tru2specs.android.store.StoreActivity;
import com.tru2specs.android.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, IDashboardView {

    private static final int CODE_LOGIN_REQUEST = 1901;
    public static final String KEY_USER_NAME = "username";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp_dashboard_offers)
    InfiniteViewPager mOffersPager;
    @BindView(R.id.tl_category)
    TabLayout mTlCategory;
    @BindView(R.id.tl_menu)
    TabLayout mTlMenu;
    @BindView(R.id.vp_category)
    ViewPager mCategoryPager;
    @BindView(R.id.vp_menu)
    ViewPager mMenuPager;
    @BindView(R.id.txt_item_in_cart_count)
    TextView mItemInCOunt;
    @BindView(R.id.drawer_layout)
    DrawerLayout mRoot;
    @BindView(R.id.img_banner)
    ImageView mAdBanner;
    @BindView(R.id.lin_services_store_locator)
    LinearLayout mStores;
    @BindView(R.id.lin_dashboard_footer_search)
    LinearLayout mSearch;
    @BindView(R.id.lin_dashboard_footer_cart)
    LinearLayout mCart;

    @BindView(R.id.btn_find_a_store)
    Button mFindAStore;

    TextView mMyAccount;

    TextView mMyOrders;

    private TextView mSignIn;
    private TextView mSignUp;
    private DashboardOffersPagerAdapter mAdapter;
    private DashboardCategoryPagerAdapter mCategoryAdapter;
    private ArrayList<String> mImages;
    private DashboardPresenter mPresenter;
    private int mCurrentPage = 0;
    private TextView mUserName;
    private DrawerLayout mDrawer;
    private LinearLayout mLayoutBeforeLogin;
    private RelativeLayout mLayoutAfterLogin;
    private ActionBarDrawerToggle toggle;
    private DashboardMenuPagerAdapter mMenuAdapter;
    private ImageView mAccount;
    @BindView(R.id.tab_indicator)
    TabLayout mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.menu);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);
        mUserName = (TextView) findViewById(R.id.txt_user_name);
        mSignIn = (TextView) findViewById(R.id.txt_dashbnard_sign_in);
        mSignUp = (TextView) findViewById(R.id.txt_dashboard_sign_up);

        mMyAccount = (TextView) headerLayout.findViewById(R.id.text_my_account);
        mAccount = (ImageView) findViewById(R.id.img_account);
        mMyOrders = (TextView) headerLayout.findViewById(R.id.txt_dashboard_my_orders);
        mLayoutBeforeLogin = (LinearLayout) findViewById(R.id.lin_before_login_options);
        mLayoutAfterLogin = (RelativeLayout) findViewById(R.id.rl_user_settings);

//        mIndicator = (CircleIndicator) findViewById(R.id.indicator);
        init();
    }

    private void init() {
        mPresenter = new DashboardPresenter(this, getApplicationContext());
        //TODO: uncomment below line to set margin in viewpager
//        mOffersPager.setPageMargin(32);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignUp();
            }
        });
        mAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(GravityCompat.START);
                navigateToProfileActivity();
            }
        });
        mMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignUp();
            }
        });
        mTlCategory.setupWithViewPager(mCategoryPager);
        mTlMenu.setupWithViewPager(mMenuPager);
        mPresenter.getDashboardData();
        //setting menu items
        setMenuItems();
        /*
        * if user is already loggedin make a call to login api, username and password is stored in
        * shared preferences. If login is successful set the response data.
        * If login is unsuccessful set previous response data stored in shared pref.
        * */
        if (new SessionManager(getApplicationContext()).isLoggedIn()) {
            mLayoutAfterLogin.setVisibility(View.VISIBLE);
            mLayoutBeforeLogin.setVisibility(View.GONE);

            /*
            * getting previously stored data in shared prefernces.
            * */

            mPresenter.doLogin();

//            Data Data = new Gson().fromJson(new SessionManager(getApplicationContext()).getUserInfo(), Data.class);
//            setData(Data);
        } else {

            HashMap<String, String> mBasicInfo = new SessionManager(getApplicationContext()).getBasicInfo();
            mUserName.setText("Hi, " + mBasicInfo.get(BasicInfoActivity.USER_KEY_FULLNAME));

            mLayoutAfterLogin.setVisibility(View.GONE);
            mLayoutBeforeLogin.setVisibility(View.VISIBLE);

        }

        setBannerAdImage();

        //TO DO Remove
//        setupViewpager();
    }

    /**
     * TO DO Remove
     * Pooja Bhave
     * Modified for UI testing
     */

    @Override
    public void setupViewpager(List<OfferHeader> offers) {

        mImages = new ArrayList<>();

        for (OfferHeader offer : offers) {
            mImages.add(offer.getImgUrl());
        }

        CustomPagerAdapter mPageAdapter = new CustomPagerAdapter(this, mImages);
        mOffersPager.setAdapter(mPageAdapter);
        mIndicator.setupWithViewPager(mOffersPager, true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_shop) {
//            showListOfStores();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_reset) {
            resetAccount();
        } else if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void resetAccount() {
        new SessionManager(getApplicationContext()).logoutUser();
        finish();
    }

    @Override
    public void navigateToSearchActivity() {
        Intent searchIntent = new Intent(DashboardActivity.this, SearchActivity.class);
        startActivity(searchIntent);
    }

    @Override
    public void navigateToCartActivity() {
        Intent searchIntent = new Intent(DashboardActivity.this, CartActivity.class);
        startActivity(searchIntent);
    }

    @Override
    public void navigateToProfileActivity() {
        Intent profileIntent = new Intent(DashboardActivity.this, ProfileActivity.class);
        startActivityForResult(profileIntent, CODE_LOGIN_REQUEST);
    }

    @Override
    public void navigateToProductListing(String menOrWomen) {
        Intent productListingIntent = new Intent(DashboardActivity.this, ProductsListActivity.class);
        startActivity(productListingIntent);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onSuccess(DashboardResponse response) {

    }

    @Override
    public void setCategoryPager(List<Type> categoriesList) {
        mCategoryAdapter = new DashboardCategoryPagerAdapter(getSupportFragmentManager());

        for (Type item : categoriesList) {
            mCategoryAdapter.addFragment(item);
        }
        mCategoryPager.setAdapter(mCategoryAdapter);
    }

    @Override
    public void setOffersPager(List<OfferHeader> offers) {
        mImages = new ArrayList<>();

        for (OfferHeader offer : offers) {
            mImages.add(offer.getImgUrl());
        }
        mAdapter = new DashboardOffersPagerAdapter(this.getSupportFragmentManager(), mImages);

//        MinFragmentPagerAdapter wrappedMinAdapter = new MinFragmentPagerAdapter(getSupportFragmentManager());
//        if (mImages.size() < 4) {
//            wrappedMinAdapter.setAdapter(mAdapter);
//        }
// wrap pager to provide infinite paging with wrap-around
//        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(wrappedMinAdapter);

        //      mOffersPager.setAdapter(mAdapter);

    }

    /**
     * Pooja Bhave
     * TO DO Remove
     */
    class CustomPagerAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;
        private int mResources[] = {
                R.drawable.bear,
                R.drawable.see_dexter,
                R.drawable.tryon_3d_banner
        };

        public CustomPagerAdapter(Context context, ArrayList<String> mImages) {
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

        private void setImage(ImageView image, String url) {
            Glide.with(DashboardActivity.this).load(url)
                    .crossFade()
                    .error(R.drawable.no_image)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

    @Override
    public void showListOfStores() {
        Intent storeIntent = new Intent(DashboardActivity.this, StoreActivity.class);
        startActivity(storeIntent);
    }

    @Override
    public void hideProgressView() {

    }

    @Override
    public void showProgressView() {

    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivityForResult(intent, CODE_LOGIN_REQUEST);
        mDrawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void navigateToSignUp() {
        Intent intent = new Intent(DashboardActivity.this, SignUpActivity.class);
        startActivityForResult(intent, CODE_LOGIN_REQUEST);
        mDrawer.closeDrawer(GravityCompat.START);
    }

    @OnClick({R.id.img_cart, R.id.lin_services_store_locator, R.id.lin_dashboard_footer_search
            , R.id.lin_dashboard_footer_cart, R.id.lin_dashboard_footer_account, R.id.btn_find_a_store})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_cart:
                break;
            case R.id.lin_services_store_locator:
            case R.id.btn_find_a_store:
                showListOfStores();
                break;
            case R.id.lin_dashboard_footer_search:
                navigateToSearchActivity();
                break;
            case R.id.lin_dashboard_footer_cart:
                navigateToCartActivity();
                break;

            case R.id.lin_dashboard_footer_account:
                navigateToProfileActivity();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_LOGIN_REQUEST && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle.containsKey(Constants.KEY_LOGIN_SIGNUP_RESPONSE)) {
                setData((Data) bundle.getParcelable(Constants.KEY_LOGIN_SIGNUP_RESPONSE));
            }
        }
    }

    //set data retruned from login or signup response
    @Override
    public void setData(Data data) {

        //create new login session
        new SessionManager(getApplicationContext()).createLoginSession(data);

        //setting user data
        User userDTO = data.getUser();
        mUserName.setText("Hi, " + userDTO.getFullName());
        mLayoutBeforeLogin.setVisibility(View.GONE);
        mLayoutAfterLogin.setVisibility(View.VISIBLE);

        //setting cart data
        Cart Cart = data.getCart();
        setCartData(Cart);
    }

    @Override
    public void setBannerAdImage() {
        String imageUrl = "http://i.imgur.com/0mTf8vo.png";
        Glide.with(DashboardActivity.this).load(imageUrl).into(mAdBanner);
    }

    @Override
    public void setMenuItems() {
        ArrayList<com.tru2specs.android.menu.model.MenuItem> items = com.tru2specs.android.menu.model.MenuItem.getStaticData();
        mMenuAdapter = new DashboardMenuPagerAdapter(getSupportFragmentManager());
        for (com.tru2specs.android.menu.model.MenuItem item : items) {
            mMenuAdapter.addFragment(item);
        }
        mMenuPager.setAdapter(mMenuAdapter);
    }

    @Override
    public void setProductList(List<Type> type) {
        DashboardVerticalRVAdapter adapter = new DashboardVerticalRVAdapter(getApplicationContext(), type);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_product_vertical_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setCartData(Cart Cart) {
        if (TextUtils.isEmpty(Cart.getCartCount())) {
            mItemInCOunt.setVisibility(View.GONE);
        } else {
            // mItemInCOunt.setVisibility(View.VISIBLE);
//            mItemInCOunt.setText(Cart.getCartCount());
        }
    }

    @Override
    public void onNetworkChange(boolean isConnected) {
        String message = "";
        int color = Color.WHITE;
        if (isConnected) {
            message = "Connected to internet";
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    //TODO:: Retry to get data from server
//
//                }
//            }, Constants.SNACKBAR_TIME_OUT);
//
//            return;
        } else {
            message = "Sorry! Not connected to internet";
        }
        Snackbar snackbar = Snackbar
                .make(mRoot, message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

}
