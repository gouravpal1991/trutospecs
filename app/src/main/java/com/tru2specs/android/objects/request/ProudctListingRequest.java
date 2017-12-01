package com.tru2specs.android.objects.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by palgour on 11/17/17.
 */

public class ProudctListingRequest {
    @SerializedName("ProductId")
    @Expose
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
