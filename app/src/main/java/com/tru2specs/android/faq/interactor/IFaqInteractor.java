package com.tru2specs.android.faq.interactor;

import android.content.Context;

import com.tru2specs.android.faq.listener.OnGetFaqsListener;
import com.tru2specs.android.lens.listener.OnGetLensesListener;
import com.tru2specs.android.objects.request.LensRequest;

/**
 * Created by ajaykumarsantra on 19/08/17.
 */

public interface IFaqInteractor {
    void onGetFaqs(Context context, OnGetFaqsListener listener);
}
