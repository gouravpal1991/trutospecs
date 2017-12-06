package com.tru2specs.android.filter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.base.BaseActivity;
import com.tru2specs.android.faq.presenter.FAQPresenter;
import com.tru2specs.android.filter.adapter.FilterKeyAdapter;
import com.tru2specs.android.filter.adapter.FilterValueAdapter;
import com.tru2specs.android.filter.listener.OnFilterKeyClickListener;
import com.tru2specs.android.filter.listener.OnFilterValueClickListener;
import com.tru2specs.android.filter.presenter.FilterPresenter;
import com.tru2specs.android.filter.view.IFilterView;
import com.tru2specs.android.objects.request.filterrequest.FilterRequest;
import com.tru2specs.android.objects.responses.filter.Data;
import com.tru2specs.android.objects.responses.filter.FilterItem;
import com.tru2specs.android.objects.responses.filter.Value;
import com.tru2specs.android.productdetails.ProductDetailsActivity;
import com.tru2specs.android.productlisting.ProductListingActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterActivity extends BaseActivity implements IFilterView, OnFilterKeyClickListener, OnFilterValueClickListener, View.OnClickListener {

    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;
    @BindView(R.id.img_back)
    ImageView mBack;

    @BindView(R.id.rl_progress)
    RelativeLayout mProgress;
    @BindView(R.id.rv_filter_keys)
    RecyclerView mKeys;
    @BindView(R.id.rv_filter_values)
    RecyclerView mValues;
    private FilterPresenter mPresenter;
    private List<FilterItem> mFilterItems = new ArrayList<>();
    private List<Value> mFilterValues = new ArrayList<>();
    HashMap<String, String> selectedFilterItems = new HashMap<>();
    private Button mApplyFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);
        init();

        mApplyFilter = (Button) findViewById(R.id.btn_apply_filter);
        mApplyFilter.setOnClickListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mKeys.setLayoutManager(mLayoutManager);
        mKeys.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        mValues.setLayoutManager(mLayoutManager1);
        mValues.setItemAnimator(new DefaultItemAnimator());


        mPresenter = new FilterPresenter(this, this);
        mPresenter.getFilterItems();
    }

    private void init() {
        hideProgressView();
        setScreenTitle();
    }

    private void setScreenTitle() {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText(getString(R.string.filter));


    }

    @Override
    public void getFilter() {

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
    public void onGetFilterFailure(String msg) {

    }

    @Override
    public void onGetFilterSuccess(Data data) {
        mFilterItems = data.getFilterItems();
        if (mFilterItems.size() > 0) {
            FilterKeyAdapter adapter = new FilterKeyAdapter(getApplicationContext(), mFilterItems, this);
            mKeys.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
        hideProgressView();
    }

    @Override
    public void onNetworkChange(boolean isConnected) {

    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onFilterKeyClick(String label, String key) {
        mFilterValues = new ArrayList<>();
        for (FilterItem item : mFilterItems) {
            if (item.getKey().equals(key)) {
                mFilterValues = item.getValues();
                break;
            }
        }

        if (mFilterValues.size() > 0) {
            //TODO:: set values for rv_filter_values
            FilterValueAdapter adapter = new FilterValueAdapter(key, getApplicationContext(), mFilterValues, this);
            mValues.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFilterValueClick(String key, String value, boolean isChecked) {
        for (FilterItem item : mFilterItems) {
            if (item.getKey().equals(key)) {
                mFilterValues = item.getValues();
                for (int i = 0; i < mFilterValues.size(); i++) {
                    if(!TextUtils.isEmpty(mFilterValues.get(i).getName())) {
                        if (mFilterValues.get(i).getName().equals(value)) {
                            mFilterValues.get(i).setChecked(isChecked);
                            break;
                        }
                    }

                }
                break;
            }
        }

        if (selectedFilterItems.containsKey(key)) {
            String selectedFilterKeyValue = selectedFilterItems.get(key);
            if (isChecked) {
                value += "," + selectedFilterKeyValue;
            } else {
                value = selectedFilterKeyValue.replace(value, "");
            }
        }
        selectedFilterItems.put(key, value);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_apply_filter:
                FilterRequest request = new FilterRequest();
                List<com.tru2specs.android.objects.request.filterrequest.FilterItem> filterItems = new ArrayList<>();
                for (Map.Entry<String, String> entry : selectedFilterItems.entrySet()) {
                    com.tru2specs.android.objects.request.filterrequest.FilterItem item = new com.tru2specs.android.objects.request.filterrequest.FilterItem();
                    item.setKey(entry.getKey());
                    item.setValue(entry.getValue());
                    filterItems.add(item);
                }
                request.setFilterItems(filterItems);

                Intent returnIntent = new Intent();
                returnIntent.putExtra(ProductListingActivity.KEY_FILTER_ITEMS, request);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                break;
        }
    }
}
