package com.tru2specs.android.store.listener;

import com.tru2specs.android.objects.responses.store.StoreAddressData;
import com.tru2specs.android.objects.responses.store.StoreListResponse;

/**
 * Created by GP00471911 on 28-06-2017.
 */

public interface OnGetStoreListener {
    void onSuccess(StoreAddressData data);
    void onFailure(String message);
}
