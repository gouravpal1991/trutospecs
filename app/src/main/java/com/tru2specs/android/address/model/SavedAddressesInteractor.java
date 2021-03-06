package com.tru2specs.android.address.model;

import com.tru2specs.android.address.listener.OnAddressSaveListener;
import com.tru2specs.android.address.listener.OnSavedAddressesListener;
import com.tru2specs.android.objects.request.AddressRequest;
import com.tru2specs.android.objects.request.SavedAddressesRequest;
import com.tru2specs.android.objects.responses.address.AddressResponse;
import com.tru2specs.android.objects.responses.savedaddresses.Address;
import com.tru2specs.android.objects.responses.savedaddresses.Data;
import com.tru2specs.android.objects.responses.savedaddresses.SavedAddressesResponse;
import com.tru2specs.android.rest.AppClient;
import com.tru2specs.android.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public class SavedAddressesInteractor implements ISavedAddressesInteractor {
    @Override
    public void onGetSavedAddresses(final OnSavedAddressesListener listener, SavedAddressesRequest request) {
        Call<SavedAddressesResponse> responseCall = AppClient.getApiService().getAddresses(request);
        responseCall.enqueue(new Callback<SavedAddressesResponse>() {
            @Override
            public void onResponse(Call<SavedAddressesResponse> call, Response<SavedAddressesResponse> response) {
                if(response.code()== Constants.STATUS_CODE_SUCCESS) {
                    int responseCode = Integer.valueOf(response.body().getResponseCode());
                    if (responseCode == Constants.STATUS_CODE_SUCCESS) {
                        Data data = response.body().getData();
                        listener.onSavedAddressSuccess(data);
                    } else if (responseCode == Constants.STATUS_CODE_RECORD_NOT_FOUND) {
                        listener.onSavedAddressFailure(response.body().getErrorMessage());
                    } else {
                        listener.onSavedAddressFailure(Constants.SOMETHING_WENT_WRONG_MSG);
                    }
                }else {
                    listener.onSavedAddressFailure(Constants.SOMETHING_WENT_WRONG_MSG);
                }
            }

            @Override
            public void onFailure(Call<SavedAddressesResponse> call, Throwable t) {
                listener.onSavedAddressFailure(t.getMessage());
            }
        });
    }

    @Override
    public void onUpdateAddress(final OnSavedAddressesListener listener, Address request) {
        Call<AddressResponse> responseCall = AppClient.getApiService().updateAddress(request);
        responseCall.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                if(response.code()== Constants.STATUS_CODE_SUCCESS) {
                    int responseCode = Integer.valueOf(response.body().getResponseCode());
                    if (responseCode == Constants.STATUS_CODE_SUCCESS) {
                        com.tru2specs.android.objects.responses.address.Data data = response.body().getData();
                        listener.onUpdateAddressSuccess(data);
                    } else if (responseCode == Constants.STATUS_CODE_RECORD_NOT_FOUND) {
                        listener.onSavedAddressFailure(response.body().getErrorMessage());
                    } else {
                        listener.onSavedAddressFailure(Constants.SOMETHING_WENT_WRONG_MSG);
                    }
                }else {
                    listener.onSavedAddressFailure(Constants.SOMETHING_WENT_WRONG_MSG);
                }
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
                listener.onSavedAddressFailure(t.getMessage());
            }
        });
    }
}
