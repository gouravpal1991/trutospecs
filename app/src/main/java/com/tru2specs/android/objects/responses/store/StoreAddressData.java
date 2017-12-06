
package com.tru2specs.android.objects.responses.store;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreAddressData {

    @SerializedName("Addresses")
    @Expose
    private List<StoreAddress> storeAddress = null;

    public List<StoreAddress> getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(List<StoreAddress> storeAddress) {
        this.storeAddress = storeAddress;
    }

}
