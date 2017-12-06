
package com.tru2specs.android.objects.responses.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagCategory implements Parcelable {

    @SerializedName("TagCatId")
    @Expose
    private Integer tagCatId;
    @SerializedName("TagCategory")
    @Expose
    private String tagCategory;

    public Integer getTagCatId() {
        return tagCatId;
    }

    public void setTagCatId(Integer tagCatId) {
        this.tagCatId = tagCatId;
    }

    public String getTagCategory() {
        return tagCategory;
    }

    public void setTagCategory(String tagCategory) {
        this.tagCategory = tagCategory;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.tagCatId);
        dest.writeString(this.tagCategory);
    }

    public TagCategory() {
    }

    protected TagCategory(Parcel in) {
        this.tagCatId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tagCategory = in.readString();
    }

    public static final Parcelable.Creator<TagCategory> CREATOR = new Parcelable.Creator<TagCategory>() {
        @Override
        public TagCategory createFromParcel(Parcel source) {
            return new TagCategory(source);
        }

        @Override
        public TagCategory[] newArray(int size) {
            return new TagCategory[size];
        }
    };
}
