package com.tru2specs.android.objects.responses.filter;

/**
 * Created by palgour on 12/3/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterResponse {

    @SerializedName("ResponseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("Data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}