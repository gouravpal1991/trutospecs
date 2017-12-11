package com.tru2specs.android.store.model;

import com.tru2specs.android.store.listener.OnGetStoreListener;

/**
 * Created by GP00471911 on 28-06-2017.
 */

public interface IStoreInteractor {
    void fetchStoresList(OnGetStoreListener listener);
}
