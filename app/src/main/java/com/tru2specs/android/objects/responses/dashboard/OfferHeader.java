package com.tru2specs.android.objects.responses.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by palgour on 9/26/17.
 */

public class OfferHeader implements Parcelable {
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("ImageType")
    @Expose
    private String imgType;
    @SerializedName("ImageURL")
    @Expose
    private String imgUrl;
    @SerializedName("Comments")
    @Expose
    private String comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.imgType);
        dest.writeString(this.imgUrl);
        dest.writeString(this.comments);
    }

    public OfferHeader() {
    }

    protected OfferHeader(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.imgType = in.readString();
        this.imgUrl = in.readString();
        this.comments = in.readString();
    }

    public static final Parcelable.Creator<OfferHeader> CREATOR = new Parcelable.Creator<OfferHeader>() {
        @Override
        public OfferHeader createFromParcel(Parcel source) {
            return new OfferHeader(source);
        }

        @Override
        public OfferHeader[] newArray(int size) {
            return new OfferHeader[size];
        }
    };
}
