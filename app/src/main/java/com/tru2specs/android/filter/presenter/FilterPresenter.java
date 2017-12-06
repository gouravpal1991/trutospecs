package com.tru2specs.android.filter.presenter;

import android.content.Context;

import com.tru2specs.android.filter.listener.IFilterListener;
import com.tru2specs.android.filter.model.FilterInteractor;
import com.tru2specs.android.filter.view.IFilterView;
import com.tru2specs.android.objects.responses.filter.Data;

/**
 * Created by palgour on 12/3/17.
 */

public class FilterPresenter implements IFilterPresenter, IFilterListener {
    private IFilterView mView;
    private Context mContext;
    private FilterInteractor mInteractor;

    public FilterPresenter(Context context, IFilterView view) {
        mView = view;
        mContext = context;
        mInteractor = new FilterInteractor();
    }

    @Override
    public void getFilterItems() {
        mInteractor.getFilterItems(mContext, this);
    }

    @Override
    public void onGetFilterSuccess(Data filterData) {
        mView.onGetFilterSuccess(filterData);
    }

    @Override
    public void onGetFilterFailure(String msg) {

    }
}
