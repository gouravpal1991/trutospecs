package com.tru2specs.android.faq.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.tru2specs.android.faq.interactor.FaqInteractor;
import com.tru2specs.android.faq.listener.OnGetFaqsListener;
import com.tru2specs.android.faq.view.IFaqView;
import com.tru2specs.android.lens.model.LensInteractor;
import com.tru2specs.android.lens.view.ILensesView;
import com.tru2specs.android.manager.SessionManager;
import com.tru2specs.android.objects.request.LensRequest;
import com.tru2specs.android.objects.responses.faq.Data;
import com.tru2specs.android.objects.responses.savedaddresses.Address;

/**
 * Created by palgour on 12/3/17.
 */

public class FAQPresenter implements IFAQPresenter, OnGetFaqsListener {
    private IFaqView mView;
    private Context mContext;
    private FaqInteractor mInteractor;

    public FAQPresenter(Context context, IFaqView view) {
        mView = view;
        mContext = context;
        mInteractor = new FaqInteractor();
    }

    @Override
    public void getFAQs() {
        mView.showProgressView();
        mInteractor.onGetFaqs(mContext, this);
    }


    @Override
    public void onGetFaqsSuccess(Data data) {
        mView.onGetFaqSuccess(data);
    }

    @Override
    public void onGetFaqsFailure(String message) {

    }
}
