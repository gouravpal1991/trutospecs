package com.tru2specs.android.cart.view;

import com.tru2specs.android.objects.responses.productlisting.Product;

import java.util.ArrayList;

/**
 * Created by palgour on 8/25/17.
 */

public interface ICartView {
    void setScreenTitle();
    void showNoItemInCartLayout();
    void hideNoItemInCartLayout();
    void navigateToCheckout();
void hideProgress();
    void showProgress();
    void setCartItems(ArrayList<Product> products);
    void onFailure(String msg);
    void removeItemFromCart(String productId);
}
