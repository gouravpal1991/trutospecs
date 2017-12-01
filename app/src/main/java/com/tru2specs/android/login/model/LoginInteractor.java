package com.tru2specs.android.login.model;

import android.util.Log;

import com.tru2specs.android.login.listerner.OnLoginListener;
import com.tru2specs.android.objects.User;
import com.tru2specs.android.objects.request.LoginRequest;
import com.tru2specs.android.objects.responses.loginSignup.Data;
import com.tru2specs.android.objects.responses.loginSignup.LoginSignUpResponse;
import com.tru2specs.android.rest.APIService;
import com.tru2specs.android.rest.AppClient;
import com.tru2specs.android.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ajaykumarsantra on 03/07/17.
 */

public class LoginInteractor implements ILoginInteractor {
    @Override
    public void submitLogin(final OnLoginListener listener, LoginRequest request) {
        Call<LoginSignUpResponse> loginSignUpResponseCall = AppClient.getApiService().doLogin(request);
        loginSignUpResponseCall.enqueue(new Callback<LoginSignUpResponse>() {
            @Override
            public void onResponse(Call<LoginSignUpResponse> call, Response<LoginSignUpResponse> response) {
                if(response.body()!=null) {
                    int responseCode = Integer.valueOf(response.body().getResponseCode());
                    if (responseCode == Constants.STATUS_CODE_SUCCESS) {
                        Data data = response.body().getData();
                        listener.onLoginSuccess(data);
                    } else if (responseCode == Constants.STATUS_CODE_UNAUTHORIZED) {
                        listener.onLoginFailure(response.body().getErrorMessage());
                    } else {
                        listener.onLoginFailure(Constants.SOMETHING_WENT_WRONG_MSG);
                    }
                }else{
                    listener.onLoginFailure(Constants.SOMETHING_WENT_WRONG_MSG);
                }
            }

            @Override
            public void onFailure(Call<LoginSignUpResponse> call, Throwable t) {
                listener.onLoginFailure(t.getMessage());
            }
        });
    }
}
