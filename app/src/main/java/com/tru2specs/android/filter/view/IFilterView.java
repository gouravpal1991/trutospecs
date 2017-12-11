package com.tru2specs.android.filter.view;

import com.tru2specs.android.objects.responses.filter.Data;

/**
 * Created by palgour on 12/3/17.
 */

public interface IFilterView {
    void getFilter();
    void hideProgressView();
    void showProgressView();
    void onGetFilterFailure(String msg);
    void onGetFilterSuccess(Data data);
}
