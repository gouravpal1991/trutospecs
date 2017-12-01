package com.tru2specs.android.dashboard.listener;
import com.tru2specs.android.objects.responses.dashboard.DashboardResponse;
import com.tru2specs.android.objects.responses.dashboard.Data;

/**
 * Created by GP00471911 on 29-06-2017.
 */

public interface OnDashboardListener {
    void onSuccess(Data data);
    void onFailure(String message);
}
