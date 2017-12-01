package com.tru2specs.android.address.listener;

import com.tru2specs.android.objects.responses.address.Data;

/**
 * Created by ajaykumarsantra on 19/08/17.
 */

public interface OnAddressSaveListener {
        void onAddAddressSuccess(Data data);
        void onAddAddressFailure(String message);
}
