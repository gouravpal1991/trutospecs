
package com.tru2specs.android.objects.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserRequest {

    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("ContactNo")
    @Expose
    private String contactNo;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("DOB")
    @Expose
    private String dOB;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return dOB;
    }

    public void setDOB(String dOB) {
        this.dOB = dOB;
    }

}
