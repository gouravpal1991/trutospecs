package com.tru2specs.android.objects.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Selva
 */

public class SearchRequest {

    @SerializedName("SearchText")
    @Expose
    private String searchText;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
