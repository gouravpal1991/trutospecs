package com.tru2specs.android.faq.interactor;

import android.content.Context;

import com.google.gson.Gson;
import com.tru2specs.android.faq.listener.OnGetFaqsListener;
import com.tru2specs.android.lens.listener.OnGetLensesListener;
import com.tru2specs.android.objects.request.LensRequest;
import com.tru2specs.android.objects.responses.faq.FaqResponse;
import com.tru2specs.android.objects.responses.lens.LensResponse;
import com.tru2specs.android.util.Helper;

/**
 * Created by palgour on 12/3/17.
 */

public class FaqInteractor implements IFaqInteractor {

    @Override
    public void onGetFaqs(Context context, OnGetFaqsListener listener) {
        //TODO:: replace faq json with API response
        FaqResponse faqResponse = new Gson().fromJson(Helper.loadJSONFromAsset(context, "faq.json"), FaqResponse.class);
        listener.onGetFaqsSuccess(faqResponse.getData());
    }
}
