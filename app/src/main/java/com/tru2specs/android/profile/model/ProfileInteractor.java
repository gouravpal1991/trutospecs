package com.tru2specs.android.profile.model;

import com.tru2specs.android.objects.request.SignUpRequest;
import com.tru2specs.android.objects.request.UpdateUserRequest;
import com.tru2specs.android.objects.responses.loginSignup.Data;
import com.tru2specs.android.objects.responses.loginSignup.LoginSignUpResponse;
import com.tru2specs.android.profile.listener.OnUpdateUserFinishedListener;
import com.tru2specs.android.rest.AppClient;
import com.tru2specs.android.signup.listener.OnSignUpFinishedListener;
import com.tru2specs.android.signup.model.ISignUpInteractor;
import com.tru2specs.android.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public class ProfileInteractor implements IProfileInteractor {
    @Override
    public void submitProfileUpdate(final OnUpdateUserFinishedListener listener, UpdateUserRequest request) {

        Call<LoginSignUpResponse> loginSignUpResponseCall = AppClient.getApiService().updateUser(request);
        loginSignUpResponseCall.enqueue(new Callback<LoginSignUpResponse>() {
            @Override
            public void onResponse(Call<LoginSignUpResponse> call, Response<LoginSignUpResponse> response) {
                if(response.code()== Constants.STATUS_CODE_SUCCESS) {
                    int responseCode = Integer.valueOf(response.body().getResponseCode());
                    if (responseCode == Constants.STATUS_CODE_SUCCESS) {
                        Data data = response.body().getData();
                        listener.onUpdateUserSucces(data);
                    } else if (responseCode == Constants.STATUS_CODE_UNAUTHORIZED) {
                        listener.onUpdateUserFailure(response.body().getErrorMessage());
                    } else {
                        listener.onUpdateUserFailure(Constants.SOMETHING_WENT_WRONG_MSG);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginSignUpResponse> call, Throwable t) {
                listener.onUpdateUserFailure(t.getMessage());
            }
        });
    }
}
