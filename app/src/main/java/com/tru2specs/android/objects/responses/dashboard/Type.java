
package com.tru2specs.android.objects.responses.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type implements Parcelable {

    @SerializedName("ProductTypeId")
    @Expose
    private Integer typeId;
    @SerializedName("ProductType")
    @Expose
    private String productType;
    @SerializedName("ProductDetails")
    @Expose
    private List<DashboardProduct> tagCategory = null;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<DashboardProduct> getTagCategory() {
        return tagCategory;
    }

    public void setTagCategory(List<DashboardProduct> tagCategory) {
        this.tagCategory = tagCategory;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.typeId);
        dest.writeString(this.productType);
        dest.writeList(this.tagCategory);
    }

    public Type() {
    }

    protected Type(Parcel in) {
        this.typeId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.productType = in.readString();
        this.tagCategory = new ArrayList<DashboardProduct>();
        in.readList(this.tagCategory, TagCategory.class.getClassLoader());
    }

    public static final Parcelable.Creator<Type> CREATOR = new Parcelable.Creator<Type>() {
        @Override
        public Type createFromParcel(Parcel source) {
            return new Type(source);
        }

        @Override
        public Type[] newArray(int size) {
            return new Type[size];
        }
    };
}
