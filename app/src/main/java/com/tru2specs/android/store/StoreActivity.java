package com.tru2specs.android.store;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tru2specs.android.R;
import com.tru2specs.android.objects.responses.store.StoreAddress;
import com.tru2specs.android.store.adapter.StoreListItemAdapter;
import com.tru2specs.android.store.presenter.StorePresenter;
import com.tru2specs.android.store.view.IStoreView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by GP00471911 on 27-06-2017.
 */

public class StoreActivity extends AppCompatActivity implements IStoreView, OnMapReadyCallback {

    @BindView(R.id.img_back)
    ImageView mBack;
    @BindView(R.id.rv_stores)
    RecyclerView mStoresList;
    @BindView(R.id.rl_progress)
    RelativeLayout mProgress;
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;

    private StorePresenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private StoreListItemAdapter mAdapter;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
       setScreenTitle();
        hideProgressView();
        mPresenter = new StorePresenter(this);
        mPresenter.getStores();




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void storeListFetchingFailed(String message) {
        Toast.makeText(StoreActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void storeListFetchingSuccess(List<StoreAddress> mList) {
        List<StoreAddress> stores = mList;
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//        mDashboardItems.setHasFixedSize(true);
        mStoresList.setLayoutManager(mLayoutManager);
        mStoresList.setHasFixedSize(true);
        mAdapter = new StoreListItemAdapter(StoreActivity.this, mList);
        mStoresList.setAdapter(mAdapter);
        createMarker(mList);
    }

    @Override
    public void hideProgressView() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showProgressView() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
    }

    private void createMarker(List<StoreAddress> stores) {
        if (stores.size() > 0) {
            LatLng firstMarkerLatLng = new LatLng(Double.valueOf(stores.get(0).getLatitude()),
                    Double.valueOf(stores.get(0).getLongitude()));
            for (StoreAddress store : stores) {
                mMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(store.getLatitude()),
                        Double.valueOf(store.getLongitude()))).title(store.getAddressLine1() + "\n" + store.getAddressLine2() + "\n" + store.getCity() + ", " + store.getPincode() + "\n" + store.getContact()))
                        .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_shop_map));
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(firstMarkerLatLng, 15.0f));
        }
    }

    @Override
    public void setScreenTitle() {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        mScreenTitle.setText(getString(R.string.title_store));
    }
}
