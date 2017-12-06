package com.tru2specs.android.objects.responses.lens;

/**
 * Created by palgour on 12/3/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lenses {

    @SerializedName("lens_name")
    @Expose
    private String lensName;
    @SerializedName("lens_brand_name")
    @Expose
    private String lensBrandName;
    @SerializedName("lens_brand_image")
    @Expose
    private String lensBrandImage;
    @SerializedName("lens_desc")
    @Expose
    private String lensDesc;
    @SerializedName("price")
    @Expose
    private String price;

    public String getLensName() {
        return lensName;
    }

    public void setLensName(String lensName) {
        this.lensName = lensName;
    }

    public String getLensBrandName() {
        return lensBrandName;
    }

    public void setLensBrandName(String lensBrandName) {
        this.lensBrandName = lensBrandName;
    }

    public String getLensBrandImage() {
        return lensBrandImage;
    }

    public void setLensBrandImage(String lensBrandImage) {
        this.lensBrandImage = lensBrandImage;
    }

    public String getLensDesc() {
        return lensDesc;
    }

    public void setLensDesc(String lensDesc) {
        this.lensDesc = lensDesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}