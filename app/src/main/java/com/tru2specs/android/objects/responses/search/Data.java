package com.tru2specs.android.objects.responses.search;

/**
 * Created by Selva
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tru2specs.android.objects.responses.productlisting.Product;

import java.util.List;

public class Data {

    @SerializedName("Products")
    @Expose
    private List<Product> products = null;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}