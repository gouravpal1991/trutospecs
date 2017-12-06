package com.tru2specs.android.objects.responses.lens;

/**
 * Created by palgour on 12/3/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("lenses")
    @Expose
    private List<Lenses> lenses = null;

    public List<Lenses> getLenses() {
        return lenses;
    }

    public void setLenses(List<Lenses> lenses) {
        this.lenses = lenses;
    }

}