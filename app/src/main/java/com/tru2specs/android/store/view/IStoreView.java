package com.tru2specs.android.store.view;

import com.tru2specs.android.objects.responses.store.StoreAddress;
import com.tru2specs.android.objects.responses.store.StoreListResponse;

import java.util.List;

/**
 * Created by GP00471911 on 27-06-2017.
 */

public interface IStoreView {
    void setScreenTitle();
    void storeListFetchingFailed(String message);

    void storeListFetchingSuccess(List<StoreAddress> mList);

    void hideProgressView();

    void showProgressView();
}
