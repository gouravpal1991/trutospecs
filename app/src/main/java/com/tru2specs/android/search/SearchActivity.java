package com.tru2specs.android.search;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tru2specs.android.R;
import com.tru2specs.android.base.BaseActivity;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.search.adapter.SearchResultProductListAdapter;
import com.tru2specs.android.search.presenter.SearchPresenter;
import com.tru2specs.android.search.view.ISearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ajaykumarsantra on 18/07/17.
 */

public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener, ISearchView {

    private static final String TAG = SearchActivity.class.getName();

    @BindView(R.id.recyclerViewSearch)
    RecyclerView mRecyclerViewSearch;

    @BindView(R.id.textSearchResultCount)
    TextView mTextViewResultCount;

    @BindView(R.id.rl_progress)
    RelativeLayout mProgress;

    private SearchResultProductListAdapter mSearchResultProductListAdapter;
    private SearchPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        mPresenter = new SearchPresenter(this, this);
        mProgress.setBackgroundColor(getResources().getColor(R.color.white));
        hideProgress();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.requestFocus();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.requestFocus();
            }
        });
        searchView.setOnQueryTextListener(this);

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                resetResults();
                return true;
            }
        });

        return true;
    }

    @Override
    public void onNetworkChange(boolean isConnected) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        if(query != null && query.length() != 0) {
            Log.d(TAG, "Search Result for :" + query);
            showProgress();
            mPresenter.attemptFetchSearchItems(query);
           // Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(SearchActivity.this, "Please search valid text", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d(TAG, newText);
        if(newText != null && newText.trim().length() == 0) {
            resetResults();
        }
        return true;
    }

    @Override
    public void setSearchResultProductsList(List<Product> productsList) {

        Log.d(TAG, "Search Result Products List size :" + productsList.size());
        hideProgress();

        if(productsList.size() > 0) {

            mTextViewResultCount.setText(productsList.size() + " Results Found");
            setSearchResults(productsList);
        }
        else {
            mTextViewResultCount.setText("No Results Found");
            List<Product> list = new ArrayList<Product>();
            setSearchResults(list);
        }
    }

    private void resetResults() {

        mTextViewResultCount.setText("No Results Found");
        List<Product> list = new ArrayList<Product>();
        setSearchResults(list);
    }

    private void setSearchResults(List<Product> list) {

        mSearchResultProductListAdapter = new SearchResultProductListAdapter(getApplicationContext(), list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerViewSearch.setLayoutManager(mLayoutManager);
        mRecyclerViewSearch.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewSearch.setAdapter(mSearchResultProductListAdapter);

        mRecyclerViewSearch.setAdapter(mSearchResultProductListAdapter);

        mSearchResultProductListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }
}
