package com.tru2specs.android.objects.request;

/**
 * Created by ajaykumarsantra on 07/07/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("UserId")
    @Expose
    private String email;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("DiveceDetails")
    @Expose
    private String diveceDetails;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDiveceDetails() {
        return diveceDetails;
    }

    public void setDiveceDetails(String diveceDetails) {
        this.diveceDetails = diveceDetails;
    }

}