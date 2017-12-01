package com.tru2specs.android.address.model;

import com.tru2specs.android.address.listener.OnAddressSaveListener;
import com.tru2specs.android.objects.request.AddressRequest;
import com.tru2specs.android.objects.responses.address.AddressResponse;


import com.tru2specs.android.objects.responses.address.Data;
import com.tru2specs.android.rest.AppClient;
import com.tru2specs.android.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ajaykumarsantra on 19/08/17.
 */

public class AddressInteractor implements IAddressInteractor {

    @Override
    public void onSubmitAddress(final OnAddressSaveListener listener, AddressRequest request) {
        Call<AddressResponse> responseCall = AppClient.getApiService().addAddress(request);
        responseCall.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                if(response.code()== Constants.STATUS_CODE_SUCCESS) {
                    int responseCode = Integer.valueOf(response.body().getResponseCode());
                    if (responseCode == Constants.STATUS_CODE_SUCCESS) {
                        Data data = response.body().getData();
                        listener.onAddAddressSuccess(data);
                    } else if (responseCode == Constants.STATUS_CODE_UNAUTHORIZED) {
                        listener.onAddAddressFailure(response.body().getErrorMessage());
                    } else {
                        listener.onAddAddressFailure(Constants.SOMETHING_WENT_WRONG_MSG);
                    }
                }
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
                listener.onAddAddressFailure(t.getMessage());
            }
        });
    }
}
