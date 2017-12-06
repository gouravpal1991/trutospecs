package com.tru2specs.android.search.presenter;

import android.content.Context;

import com.tru2specs.android.objects.request.SearchRequest;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.objects.responses.search.Data;
import com.tru2specs.android.search.listener.OnSearchResponseListener;
import com.tru2specs.android.search.model.SearchInteractor;
import com.tru2specs.android.search.view.ISearchView;

import java.util.List;

/**
 * Created by Selva
 */

public class SearchPresenter implements ISearchPresenter, OnSearchResponseListener {

    Context mContext;
    private final ISearchView mView;
    private SearchInteractor mInteractor;

    public SearchPresenter(Context context, ISearchView view) {
        mContext = context;
        mView = view;
        mInteractor = new SearchInteractor();
    }

    @Override
    public void attemptFetchSearchItems(String textToSearch) {
        SearchRequest request = new SearchRequest();
        request.setSearchText(textToSearch);

        mInteractor.getSearchedData(this, request);
    }

    @Override
    public void onSuccess(Data data) {

        List<Product> productList = data.getProducts();
        mView.setSearchResultProductsList(productList);
    }

    @Override
    public void onFailure(String message) {
        mView.hideProgress();
    }
}
