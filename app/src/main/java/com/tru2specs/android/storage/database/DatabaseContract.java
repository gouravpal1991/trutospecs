package com.tru2specs.android.storage.database;

import android.provider.BaseColumns;

/**
 * Created by Selva
 */

public class DatabaseContract {

    private DatabaseContract(){}

    public static class Favourite implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_FAV_PRODUCT_ID = "product_id";
    }

    public static class CartProducts implements BaseColumns {
        public static final String TABLE_NAME = "cart_items";
        public static final String COLUMN_CART_PRODUCT_ID = "product_id";

    }
}
