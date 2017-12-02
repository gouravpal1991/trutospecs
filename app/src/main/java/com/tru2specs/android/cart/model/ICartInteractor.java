package com.tru2specs.android.cart.model;

import com.tru2specs.android.cart.listener.OnCartItemsFetchListerner;
import com.tru2specs.android.favorite.listener.OnFavItemsFetchListerner;
import com.tru2specs.android.objects.request.CartRequest;

/**
 * Created by palgour on 11/19/17.
 */

public interface ICartInteractor {
    void getCartProducts(OnCartItemsFetchListerner listerner, CartRequest request);
    void getFavProducts(OnFavItemsFetchListerner listerner, CartRequest request);
}
