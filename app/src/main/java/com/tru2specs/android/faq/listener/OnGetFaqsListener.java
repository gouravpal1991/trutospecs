package com.tru2specs.android.faq.listener;

import com.tru2specs.android.objects.responses.faq.Data;

/**
 * Created by palgour on 12/3/17.
 */

public interface OnGetFaqsListener {
    void onGetFaqsSuccess(Data data);
    void onGetFaqsFailure(String message);
}
