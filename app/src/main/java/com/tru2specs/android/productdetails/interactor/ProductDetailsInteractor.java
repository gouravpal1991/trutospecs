package com.tru2specs.android.productdetails.interactor;

import com.tru2specs.android.objects.request.ProudctListingRequest;
import com.tru2specs.android.objects.responses.address.AddressResponse;
import com.tru2specs.android.objects.responses.productlisting.Data;
import com.tru2specs.android.objects.responses.productlisting.ProductListingResponse;
import com.tru2specs.android.productdetails.listener.OnProductDetailsListener;
import com.tru2specs.android.rest.AppClient;
import com.tru2specs.android.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by palgour on 11/17/17.
 */

public class ProductDetailsInteractor implements IProductDetailsInteractor {

    @Override
    public void getProductDetailsById(final OnProductDetailsListener listener, ProudctListingRequest request) {
        Call<ProductListingResponse> responseCall = AppClient.getApiService().getProductById(request);
        responseCall.enqueue(new Callback<ProductListingResponse>() {
            @Override
            public void onResponse(Call<ProductListingResponse> call, Response<ProductListingResponse> response) {
                if(response.code()== Constants.STATUS_CODE_SUCCESS) {
                    int responseCode = Integer.valueOf(response.body().getResponseCode());
                    if (responseCode == Constants.STATUS_CODE_SUCCESS) {
                        Data data = response.body().getData();
                        listener.onSuccess(data);
                    } else if (responseCode == Constants.STATUS_CODE_UNAUTHORIZED) {
                        listener.onFailure(response.body().getErrorMessage());
                    } else {
                        listener.onFailure(Constants.SOMETHING_WENT_WRONG_MSG);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductListingResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
