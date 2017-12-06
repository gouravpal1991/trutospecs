package com.tru2specs.android.storage.database;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by Selva
 */

public class DatabaseManager {

    private static String TAG = DatabaseManager.class.getName();
    private DatabaseHelper databaseHelper;
    private static DatabaseManager sInstance;

    public static DatabaseManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseManager(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseManager(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void addFavourites(String productId) {
        Log.d(TAG, "addFavourites ::: productId :" + productId);
        if(productId != null)
            databaseHelper.addFavorite(productId);
    }

    public void deleteFavourites(String productId) {
        Log.d(TAG," deleteFavourites ::: productId :" + productId );
        if(productId != null)
            databaseHelper.removeFavorite(productId);
    }

    public ArrayList<String> getFavouriteContactIds() {
        Log.d(TAG," getFavouriteContactIds ");
        return databaseHelper.getAllFavoriteProductIds();
    }

    public void addCartProducts(String productId) {
        Log.d(TAG," addCartProducts :: productId :" + productId);
        if(productId != null)
            databaseHelper.addToCartProducts(productId);
    }

    public void deleteCartProduct(String productId) {
        Log.d(TAG," deleteCartProduct :: productId :" + productId);
        if(productId != null)
            databaseHelper.removeCartProducts(productId);
    }

    public ArrayList<String> getCartProducts() {
        Log.d(TAG," getCartProducts ");
       return databaseHelper.getAllCartProducts();
    }

    public int getCartProductsCount() {
        Log.d(TAG," getCartProductsCount ");
        return databaseHelper.getCartProductsCount();
    }

    public int getFavoriteProductsCount() {
        Log.d(TAG," getFavoriteProductsCount ");
        return databaseHelper.getFavoriteProductsCount();
    }

}