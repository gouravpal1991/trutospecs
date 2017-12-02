package com.tru2specs.android.favorite.view;

import com.tru2specs.android.objects.responses.productlisting.Product;

import java.util.ArrayList;

/**
 * Created by Selva
 */

public interface IFavoriteView {
    void setScreenTitle();
    void showNoItemInFavLayout();
    void hideNoItemInFavLayout();
    void setFavoriteItems(ArrayList<Product> products);
    void onFailure(String msg);
    void removeItemFromFavorites(String productId);
    void hideProgress();
    void showProgress();
}
