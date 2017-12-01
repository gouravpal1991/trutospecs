package com.tru2specs.android.dashboard.model;

import com.tru2specs.android.dashboard.listener.OnDashboardListener;

/**
 * Created by GP00471911 on 29-06-2017.
 */

public interface IDashboardInteractor {
    void getDashboardData(OnDashboardListener listener);
}
