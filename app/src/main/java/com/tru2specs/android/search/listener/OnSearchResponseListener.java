package com.tru2specs.android.search.listener;
import com.tru2specs.android.objects.responses.search.Data;

/**
 * Created by Selva
 */

public interface OnSearchResponseListener {

    void onSuccess(Data data);
    void onFailure(String message);
}
