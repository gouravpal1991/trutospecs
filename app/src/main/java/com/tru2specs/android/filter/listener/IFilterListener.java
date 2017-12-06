package com.tru2specs.android.filter.listener;

import com.tru2specs.android.objects.responses.filter.Data;

/**
 * Created by palgour on 12/3/17.
 */

public interface IFilterListener {
    void onGetFilterSuccess(Data filterData);
    void onGetFilterFailure(String msg);
}
