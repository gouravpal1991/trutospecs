
package com.tru2specs.android.objects.responses.dashboard;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("Type")
    @Expose
    private List<Type> type = null;
    @SerializedName("Banner")
    @Expose
    private List<OfferHeader> banner = null;
    @SerializedName("NewArrivals")
    @Expose
    private List<NewArrivals> newArrivals = null;
    @SerializedName("HotDeals")
    @Expose
    private List<Object> hotDeals = null;

    public List<Type> getType() {
        return type;
    }

    public void setType(List<Type> type) {
        this.type = type;
    }

    public List<OfferHeader> getBanner() {
        return banner;
    }

    public void setBanner(List<OfferHeader> banner) {
        this.banner = banner;
    }

    public List<NewArrivals> getNewArrivals() {
        return newArrivals;
    }

    public void setNewArrivals(List<NewArrivals> newArrivals) {
        this.newArrivals = newArrivals;
    }

    public List<Object> getHotDeals() {
        return hotDeals;
    }

    public void setHotDeals(List<Object> hotDeals) {
        this.hotDeals = hotDeals;
    }

}
