package com.tru2specs.android.dashboard.model;

import com.tru2specs.android.dashboard.listener.OnDashboardListener;
import com.tru2specs.android.objects.responses.dashboard.DashboardResponse;
import com.tru2specs.android.rest.AppClient;
import com.tru2specs.android.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by GP00471911 on 29-06-2017.
 */

public class DashboardInteractor implements IDashboardInteractor {
    @Override
    public void getDashboardData(final OnDashboardListener listener) {
        Call<DashboardResponse> dashboardResponseCall = AppClient.getApiService().getDashboardData();
        dashboardResponseCall.enqueue(new Callback<DashboardResponse>() {
            @Override
            public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
                if (response.code() == Constants.STATUS_CODE_SUCCESS) {
                    DashboardResponse dashboardResponse = response.body();
                    if (dashboardResponse.getResponseCode() == Constants.STATUS_CODE_SUCCESS) {
                        listener.onSuccess(dashboardResponse.getData());
                    } else {
                        listener.onFailure(dashboardResponse.getErrorMessage());
                    }
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<DashboardResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
