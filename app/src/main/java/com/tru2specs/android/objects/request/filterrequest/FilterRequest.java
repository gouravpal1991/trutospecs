package com.tru2specs.android.objects.request.filterrequest;

/**
 * Created by palgour on 12/3/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterRequest implements Parcelable {

    @SerializedName("filterItems")
    @Expose
    private List<FilterItem> filterItems = null;

    public List<FilterItem> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<FilterItem> filterItems) {
        this.filterItems = filterItems;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.filterItems);
    }

    public FilterRequest() {
    }

    protected FilterRequest(Parcel in) {
        this.filterItems = new ArrayList<FilterItem>();
        in.readList(this.filterItems, FilterItem.class.getClassLoader());
    }

    public static final Parcelable.Creator<FilterRequest> CREATOR = new Parcelable.Creator<FilterRequest>() {
        @Override
        public FilterRequest createFromParcel(Parcel source) {
            return new FilterRequest(source);
        }

        @Override
        public FilterRequest[] newArray(int size) {
            return new FilterRequest[size];
        }
    };
}