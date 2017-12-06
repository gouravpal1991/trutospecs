package com.tru2specs.android.cart.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.tru2specs.android.cart.listener.OnCartItemsFetchListerner;
import com.tru2specs.android.cart.model.CartInteractor;
import com.tru2specs.android.cart.view.ICartView;
import com.tru2specs.android.objects.request.CartRequest;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.storage.database.DatabaseManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
       // ArrayList<String> productIds = CartManager.getCartInstance(mContext).getmCartItems();
        ArrayList<String> productIds = DatabaseManager.getInstance(mContext).getCartProducts();
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

        // remove duplicates if any
        Map<Integer, Product> map = new LinkedHashMap<>();
        for (Product product : products) {
            map.put(product.getProductId(), product);
        }
        products.clear();
        products.addAll(map.values());

        Log.d("FetchSucces Cart count:" , ""+products.size());
        mView.setCartItems(products);
    }

    @Override
    public void onFetchFailure(String msg) {
        mView.hideProgress();
        mView.onFailure(msg);
    }
}
