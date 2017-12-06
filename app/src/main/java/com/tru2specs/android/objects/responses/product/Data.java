package com.tru2specs.android.objects.responses.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PB00471065 on 11/16/2017.
 */

public class Data {
    @SerializedName("Products")
    @Expose
    private List<com.tru2specs.android.objects.responses.product.Products> Products = null;

    public List<com.tru2specs.android.objects.responses.product.Products> getProducts() {
        return Products;
    }
}
