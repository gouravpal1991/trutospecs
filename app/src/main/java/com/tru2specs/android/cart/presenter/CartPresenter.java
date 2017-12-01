package com.tru2specs.android.cart.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.google.zxing.common.StringUtils;
import com.tru2specs.android.cart.listener.OnCartItemsFetchListerner;
import com.tru2specs.android.cart.model.CartInteractor;
import com.tru2specs.android.cart.view.ICartView;
import com.tru2specs.android.manager.CartManager;
import com.tru2specs.android.objects.request.CartRequest;
import com.tru2specs.android.objects.responses.productlisting.Product;

import java.util.ArrayList;

/**
 * Created by palgour on 11/19/17.
 */

public class CartPresenter implements ICartPresenter, OnCartItemsFetchListerner {

    Context mContext;
    private final ICartView mView;
    private CartInteractor mInteractor;

    public CartPresenter(Context context, ICartView view) {
        mContext = context;
        mView = view;
        mInteractor = new CartInteractor();
    }

    @Override
    public void attemptFetchCartItems() {
        CartRequest request = new CartRequest();
        ArrayList<String> productIds = CartManager.getCartInstance(mContext).getmCartItems();
        if (productIds != null && productIds.size() > 0) {
            String ids = TextUtils.join(",", productIds);
            request.setProductIds(ids);
            mInteractor.getCartProducts(this, request);
            return;
        }
       onFetchFailure("");
    }

    @Override
    public void onFetchSuccess(ArrayList<Product> products) {
        mView.setCartItems(products);
    }

    @Override
    public void onFetchFailure(String msg) {
        mView.hideProgress();
        mView.onFailure(msg);
    }
}
