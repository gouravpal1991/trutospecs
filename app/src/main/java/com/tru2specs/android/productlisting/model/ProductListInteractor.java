package com.tru2specs.android.productlisting.model;

import com.tru2specs.android.objects.responses.product.ProductResponse;
import com.tru2specs.android.productlisting.OnProductListListener;
import com.tru2specs.android.rest.AppClient;
import com.tru2specs.android.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PB00471065 on 11/16/2017.
 */

public class ProductListInteractor implements IProductListInteractor {
    @Override
    public void getProductListData(final OnProductListListener listener) {
        Call<ProductResponse> productResponseCall = AppClient.getApiService().getProductData();
        productResponseCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.code() == Constants.STATUS_CODE_SUCCESS) {
                    ProductResponse productResponse = response.body();
                    if (productResponse.getResponseCode() == Constants.STATUS_CODE_SUCCESS) {
                        listener.onSuccess(productResponse.getData());
                    } else {
                        listener.onFailure(productResponse.getErrorMessage());
                    }
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
