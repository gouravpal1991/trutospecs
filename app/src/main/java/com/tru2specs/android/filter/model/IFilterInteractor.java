package com.tru2specs.android.filter.model;

import android.content.Context;

import com.tru2specs.android.filter.listener.IFilterListener;

/**
 * Created by palgour on 12/3/17.
 */

public interface IFilterInteractor {
    void getFilterItems(Context context, IFilterListener listener);
}
