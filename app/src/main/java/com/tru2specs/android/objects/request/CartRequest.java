package com.tru2specs.android.objects.request;

/**
 * Created by palgour on 11/19/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartRequest {

    @SerializedName("ProductIds")
    @Expose
    private String productIds;

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

}