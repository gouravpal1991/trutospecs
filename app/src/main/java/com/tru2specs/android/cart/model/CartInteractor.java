package com.tru2specs.android.cart.model;

import com.tru2specs.android.cart.listener.OnCartItemsFetchListerner;
import com.tru2specs.android.objects.request.CartRequest;
import com.tru2specs.android.objects.responses.productlisting.Data;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.objects.responses.productlisting.ProductListingResponse;
import com.tru2specs.android.rest.AppClient;
import com.tru2specs.android.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by palgour on 11/19/17.
 */

public class CartInteractor implements ICartInteractor {
    @Override
    public void getCartProducts(final OnCartItemsFetchListerner listerner, CartRequest request) {
        Call<ProductListingResponse> responseCall = AppClient.getApiService().getCartItems(request);
        responseCall.enqueue(new Callback<ProductListingResponse>() {
            @Override
            public void onResponse(Call<ProductListingResponse> call, Response<ProductListingResponse> response) {
                if(response.code()== Constants.STATUS_CODE_SUCCESS) {
                    int responseCode = Integer.valueOf(response.body().getResponseCode());
                    if (responseCode == Constants.STATUS_CODE_SUCCESS) {
                        Data data = response.body().getData();
                        listerner.onFetchSuccess((ArrayList<Product>) data.getProducts());
                    } else if (responseCode == Constants.STATUS_CODE_UNAUTHORIZED) {
                        listerner.onFetchFailure(response.body().getErrorMessage());
                    } else {
                        listerner.onFetchFailure(Constants.SOMETHING_WENT_WRONG_MSG);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductListingResponse> call, Throwable t) {
                listerner.onFetchFailure(t.getMessage());
            }
        });
    }
}
