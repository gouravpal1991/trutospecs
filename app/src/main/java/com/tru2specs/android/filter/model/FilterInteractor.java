package com.tru2specs.android.filter.model;

import android.content.Context;

import com.google.gson.Gson;
import com.tru2specs.android.filter.listener.IFilterListener;
import com.tru2specs.android.objects.responses.filter.FilterResponse;
import com.tru2specs.android.objects.responses.product.ProductResponse;
import com.tru2specs.android.rest.AppClient;
import com.tru2specs.android.util.Constants;
import com.tru2specs.android.util.Helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by palgour on 12/3/17.
 */

public class FilterInteractor implements IFilterInteractor {
    @Override
    public void getFilterItems(Context context, final IFilterListener listener) {
//        //TODO:: replace faq json with API response
//        FilterResponse filterResponse = new Gson().fromJson(Helper.loadJSONFromAsset(context, "filter.json"), FilterResponse.class);
//        listener.onGetFilterSuccess(filterResponse.getData());

        Call<FilterResponse> productResponseCall = AppClient.getApiService().getFilterdKeys();
        productResponseCall.enqueue(new Callback<FilterResponse>() {
            @Override
            public void onResponse(Call<FilterResponse> call, Response<FilterResponse> response) {
                if (response.code() == Constants.STATUS_CODE_SUCCESS) {
                    FilterResponse productResponse = response.body();
                    if (productResponse.getResponseCode() == Constants.STATUS_CODE_SUCCESS) {
                        listener.onGetFilterSuccess(productResponse.getData());
                    } else {
                        listener.onGetFilterFailure(productResponse.getErrorMessage());
                    }
                } else {
                    listener.onGetFilterFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<FilterResponse> call, Throwable t) {
                listener.onGetFilterFailure(t.getMessage());
            }
        });
    }
}
