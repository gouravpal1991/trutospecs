package com.tru2specs.android.productlisting.model;

import com.tru2specs.android.productlisting.OnProductListListener;

/**
 * Created by PB00471065 on 11/16/2017.
 */

public interface IProductListInteractor {
    void getProductListData(OnProductListListener listener);
}
