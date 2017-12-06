package com.tru2specs.android.manager;

import android.content.Context;

import com.tru2specs.android.objects.responses.productlisting.Product;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by palgour on 11/19/17.
 */

public class CartManager {

    private static CartManager cartManager;
    private ArrayList<String> mCartItems = new ArrayList<>();
    private HashMap<String, Product> mProducts = new HashMap<>();

    public CartManager(Context context) {

    }

    public static CartManager getCartInstance(Context context){
        if(cartManager == null){
            cartManager=new CartManager(context);
        }
        return cartManager;
    }

    public int getCartItemCount(){
        return mCartItems.size();
    }

    public ArrayList<String> getmCartItems() {
        return mCartItems;
    }

    public void setmCartItems(ArrayList<String> mCartItems) {
        this.mCartItems = mCartItems;
    }

    public void addItemInCart(String productId){
        this.mCartItems.add(productId);
    }

    public void deleteItemInCart(String productId){
        if(mCartItems.size() >0 && mCartItems.contains(productId)){
            mCartItems.remove(productId);
        }
    }

    public void addProduct(Product product){
        mProducts.put(String.valueOf(product.getProductId()),product);
    }
}
