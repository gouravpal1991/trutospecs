package com.tru2specs.android.productlisting;

import com.tru2specs.android.objects.responses.product.Data;

/**
 * Created by PB00471065 on 11/16/2017.
 */

public interface OnProductListListener {
    void onSuccess(Data data);

    void onFailure(String message);
}

