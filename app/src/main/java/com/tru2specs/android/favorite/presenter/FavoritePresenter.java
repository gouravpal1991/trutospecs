package com.tru2specs.android.favorite.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.tru2specs.android.cart.model.CartInteractor;
import com.tru2specs.android.favorite.listener.OnFavItemsFetchListerner;
import com.tru2specs.android.favorite.view.IFavoriteView;
import com.tru2specs.android.objects.request.CartRequest;
import com.tru2specs.android.objects.responses.productlisting.Product;
import com.tru2specs.android.storage.database.DatabaseManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Selva
 */

public class FavoritePresenter implements IFavoritePresenter, OnFavItemsFetchListerner {

    Context mContext;
    private final IFavoriteView mView;
    private CartInteractor mInteractor;

    public FavoritePresenter(Context context, IFavoriteView view) {
        mContext = context;
        mView = view;
        mInteractor = new CartInteractor();
    }

    @Override
    public void attemptFetchFavItems() {
        CartRequest request = new CartRequest();
       // ArrayList<String> productIds = CartManager.getCartInstance(mContext).getmFavItems();
        ArrayList<String> productIds = DatabaseManager.getInstance(mContext).getFavouriteContactIds();
        Log.d("Fav products count : " , ""+productIds.size());
        if (productIds != null && productIds.size() > 0) {
            String ids = TextUtils.join(",", productIds);
            request.setProductIds(ids);
            mInteractor.getFavProducts(this, request);
            return;
        }
       onFetchFailure("");
    }

    @Override
    public void onFetchSuccess(ArrayList<Product> products) {
       // Log.d("FetchSuccess Fav count:" , ""+products.size());

        // remove duplicates if any
        Map<Integer, Product> map = new LinkedHashMap<>();
        for (Product product : products) {
            map.put(product.getProductId(), product);
        }
        products.clear();
        products.addAll(map.values());

        Log.d("FetchSuccess Fav count:" , ""+products.size());
        mView.setFavoriteItems(products);
    }

    @Override
    public void onFetchFailure(String msg) {
        mView.hideProgress();
        mView.onFailure(msg);
    }
}
