package com.tru2specs.android.signup.model;

import com.tru2specs.android.objects.User;
import com.tru2specs.android.objects.request.SignUpRequest;
import com.tru2specs.android.objects.responses.loginSignup.Data;
import com.tru2specs.android.objects.responses.loginSignup.LoginSignUpResponse;
import com.tru2specs.android.rest.AppClient;
import com.tru2specs.android.signup.listener.OnSignUpFinishedListener;
import com.tru2specs.android.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public class SignUpInteractor implements ISignUpInteractor {
    @Override
    public void submitSignUp(final OnSignUpFinishedListener listener, SignUpRequest request) {

        String names[] = request.getFirstName().split(" ");
        if (names.length > 1) {
            request.setFirstName(names[0]);
            request.setLastName(names[1]);
        }
        String requestJson= request.toString();

        Call<LoginSignUpResponse> loginSignUpResponseCall = AppClient.getApiService().doSignUp(request);
        loginSignUpResponseCall.enqueue(new Callback<LoginSignUpResponse>() {
            @Override
            public void onResponse(Call<LoginSignUpResponse> call, Response<LoginSignUpResponse> response) {
                if(response.code()== Constants.STATUS_CODE_SUCCESS) {
                    int responseCode = Integer.valueOf(response.body().getResponseCode());
                    if (responseCode == Constants.STATUS_CODE_SUCCESS) {
                        Data data = response.body().getData();
                        listener.onSignUpSuccess(data);
                    } else if (responseCode == Constants.STATUS_CODE_UNAUTHORIZED) {
                        listener.onSignUpFailure(response.body().getErrorMessage());
                    } else {
                        listener.onSignUpFailure(response.body().getErrorMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginSignUpResponse> call, Throwable t) {
                listener.onSignUpFailure(t.getMessage());
            }
        });
    }
}
