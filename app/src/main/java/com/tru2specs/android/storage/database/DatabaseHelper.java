package com.tru2specs.android.storage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Selva
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String TAG = DatabaseHelper.class.getName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Tru2SpecDB";

    private static final String SQL_CREATE_FAVORITE_TABLE =  "CREATE TABLE " + DatabaseContract.Favourite.TABLE_NAME + " (" +
            DatabaseContract.Favourite._ID + " INTEGER PRIMARY KEY,"+
            DatabaseContract.Favourite.COLUMN_FAV_PRODUCT_ID + " TEXT " + " )";

    private static final String SQL_CREATE_CART_TABLE =  "CREATE TABLE " + DatabaseContract.CartProducts.TABLE_NAME + " (" +
            DatabaseContract.CartProducts._ID + " INTEGER PRIMARY KEY," +
            DatabaseContract.CartProducts.COLUMN_CART_PRODUCT_ID + " TEXT " + " )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addFavorite(@NonNull String productId) {
        Log.d(TAG, " addFavorite ::: productId :" + productId);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Favourite.COLUMN_FAV_PRODUCT_ID, productId); // product ID

        try {
            db.insertOrThrow(DatabaseContract.Favourite.TABLE_NAME, null, values);
        }catch (Exception e) {
            Log.d(TAG, " addFavorite ::: Exception  :" + e.getMessage());
        }
    }

    public void removeFavorite(@NonNull String productId) {

        Log.d(TAG, "removeFavourite ::: productId :" + productId );

        SQLiteDatabase db = this.getWritableDatabase();
        // delete data
        db.delete(DatabaseContract.Favourite.TABLE_NAME, DatabaseContract.Favourite.COLUMN_FAV_PRODUCT_ID + " = ?",  new String[] { productId });
    }

    public ArrayList<String> getAllFavoriteProductIds() {

        Log.d(TAG, " getAllFavoriteContactsIds ");

        ArrayList<String> favProductIdList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

       // String whereClause = DatabaseContract.Favourite.COLUMN_FAV_PRODUCT_ID +" = ?";
       // String [] whereArgs = new String[] { userId };

        Cursor cursor = db.query(DatabaseContract.Favourite.TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(DatabaseContract.Favourite.COLUMN_FAV_PRODUCT_ID));
                favProductIdList.add(id);

            } while (cursor.moveToNext());
        }
        Log.d(TAG, " getAllFavouriteContacts ::: favProductIdList size : " + favProductIdList.size());
        cursor.close();
        return favProductIdList;
    }

    // cart products database operations

    public void addToCartProducts(String productId){
        Log.d(TAG, " addToCartProducts ::: productId : "+productId);

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.CartProducts.COLUMN_CART_PRODUCT_ID, productId);

        try {
            db.insertOrThrow(DatabaseContract.CartProducts.TABLE_NAME, null, values);
        } catch (Exception e) {
            Log.d(TAG, " addToCart ::: Exception  :" + e.getMessage());
        }
    }

    public void removeCartProducts(@NonNull String productId) {

        Log.d(TAG, "removeCartProducts ::: productId :" + productId );
        SQLiteDatabase db = this.getWritableDatabase();
        // delete data
        db.delete(DatabaseContract.CartProducts.TABLE_NAME, DatabaseContract.CartProducts.COLUMN_CART_PRODUCT_ID + " = ?",  new String[] { productId });
    }


    public ArrayList<String> getAllCartProducts() {

        Log.d(TAG, " getAllCartProducts ");

        ArrayList<String> cartProductsIdList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        // String whereClause = DatabaseContract.Favourite.COLUMN_FAV_PRODUCT_ID +" = ?";
        // String [] whereArgs = new String[] { userId };

        Cursor cursor = db.query(DatabaseContract.CartProducts.TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(DatabaseContract.CartProducts.COLUMN_CART_PRODUCT_ID));
                cartProductsIdList.add(id);

            } while (cursor.moveToNext());
        }
        Log.d(TAG, " getAllFavouriteContacts ::: cartProductsIdList size : " + cartProductsIdList.size());
        cursor.close();
        return cartProductsIdList;
    }

    public int getCartProductsCount() {
        int count = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DatabaseContract.CartProducts.TABLE_NAME, null, null, null, null, null, null);
        count = cursor.getCount();
        cursor.close();
        Log.d(TAG, " getCartProductsCount :  count :" + count);
        return count;
    }

    public int getFavoriteProductsCount() {
        int count = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DatabaseContract.Favourite.TABLE_NAME, null, null, null, null, null, null);
        count =  cursor.getCount();
        cursor.close();
        Log.d(TAG, " getFavoriteProductsCount :  count :" + count);
        return count;
    }
}
