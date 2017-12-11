package com.tru2specs.android.objects.responses.filter;

/**
 * Created by palgour on 12/3/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("filterItems")
    @Expose
    private List<FilterItem> filterItems = null;

    public List<FilterItem> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<FilterItem> filterItems) {
        this.filterItems = filterItems;
    }

}