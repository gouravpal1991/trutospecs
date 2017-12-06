
package com.tru2specs.android.objects.responses.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreListResponse {

    @SerializedName("Data")
    @Expose
    private StoreAddressData data;
    @SerializedName("ResponseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("ErrorMessage")
    @Expose
    private String error;

    public StoreAddressData getData() {
        return data;
    }

    public void setData(StoreAddressData data) {
        this.data = data;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
