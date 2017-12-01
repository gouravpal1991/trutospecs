package com.tru2specs.android.store.model;

import com.tru2specs.android.objects.responses.store.StoreListResponse;
import com.tru2specs.android.rest.AppClient;
import com.tru2specs.android.store.listener.OnGetStoreListener;
import com.tru2specs.android.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by GP00471911 on 28-06-2017.
 */

public class StoreInteractor implements IStoreInteractor {
    @Override
    public void fetchStoresList(final OnGetStoreListener listener) {
        Call<StoreListResponse> storeListResponseCall = AppClient.getApiService().getStoresList();
        storeListResponseCall.enqueue(new Callback<StoreListResponse>() {
            @Override
            public void onResponse(Call<StoreListResponse> call, Response<StoreListResponse> response) {
                if (response.code() == Constants.STATUS_CODE_SUCCESS) {
                    StoreListResponse storeListResponse = response.body();
                    if (storeListResponse.getResponseCode() == Constants.STATUS_CODE_SUCCESS) {
                        listener.onSuccess(storeListResponse.getData());
                    } else {
                        listener.onFailure(storeListResponse.getError());
                    }
                } else {
                    listener.onFailure(Constants.SOMETHING_WENT_WRONG_MSG);
                }
            }

            @Override
            public void onFailure(Call<StoreListResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
