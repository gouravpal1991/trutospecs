package com.tru2specs.android.objects.responses.filter;

/**
 * Created by palgour on 12/3/17.
 */

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterItem {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("values")
    @Expose
    private List<Value> values = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
