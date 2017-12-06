package com.tru2specs.android.search.model;

import android.util.Log;

import com.tru2specs.android.objects.request.SearchRequest;
import com.tru2specs.android.objects.responses.search.SearchResponse;
import com.tru2specs.android.rest.AppClient;
import com.tru2specs.android.search.listener.OnSearchResponseListener;
import com.tru2specs.android.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Selva
 */

public class SearchInteractor implements ISearchInteractor{
    private static final String TAG = SearchInteractor.class.getName();

    @Override
    public void getSearchedData(final OnSearchResponseListener listener, SearchRequest request) {

        Call<SearchResponse> searchResponseCall = AppClient.getApiService().getSearchedProducts(request);
        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                Log.d(TAG, "Search REST response code:" + response.code());
                if (response.code() == Constants.STATUS_CODE_SUCCESS) {
                    SearchResponse searchResponse = response.body();
                    if (searchResponse.getResponseCode() == Constants.STATUS_CODE_SUCCESS) {
                        listener.onSuccess(searchResponse.getData());
                    } else {
                        listener.onFailure(searchResponse.getErrorMessage());
                    }
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
