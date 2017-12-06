package com.tru2specs.android.faq.view;


import com.tru2specs.android.objects.responses.faq.Data;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public interface IFaqView {
    void getFaq();
    void hideProgressView();
    void showProgressView();
    void onGetFaqFailure(String msg);
    void onGetFaqSuccess(Data data);
}
