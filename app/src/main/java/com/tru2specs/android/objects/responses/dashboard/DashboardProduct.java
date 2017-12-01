package com.tru2specs.android.objects.responses.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by palgour on 11/9/17.
 */

public class DashboardProduct implements Parcelable {
    @SerializedName("ProductName")
    @Expose
    private String prodName;
    @SerializedName("ProductId")
    @Expose
    private String prodId;
    @SerializedName("ImageURL")
    @Expose
    private String prodImg;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdImg() {
        return prodImg;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.prodName);
        dest.writeString(this.prodId);
        dest.writeString(this.prodImg);
    }

    public DashboardProduct() {
    }

    protected DashboardProduct(Parcel in) {
        this.prodName = in.readString();
        this.prodId = in.readString();
        this.prodImg = in.readString();
    }

    public static final Parcelable.Creator<DashboardProduct> CREATOR = new Parcelable.Creator<DashboardProduct>() {
        @Override
        public DashboardProduct createFromParcel(Parcel source) {
            return new DashboardProduct(source);
        }

        @Override
        public DashboardProduct[] newArray(int size) {
            return new DashboardProduct[size];
        }
    };
}
