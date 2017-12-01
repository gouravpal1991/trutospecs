
package com.tru2specs.android.objects.responses.savedaddresses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("ID")
    @Expose
    private Integer ID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("UserType")
    @Expose
    private String userType;
    @SerializedName("Line1")
    @Expose
    private String line1;
    @SerializedName("Line2")
    @Expose
    private String line2;
    @SerializedName("Line3")
    @Expose
    private Object line3;
    @SerializedName("Landmark")
    @Expose
    private String landmark;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Pincode")
    @Expose
    private String pinCode;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Phone1")
    @Expose
    private String phoneNo1;
    @SerializedName("Phone2")
    @Expose
    private String phoneNo2;
    @SerializedName("IsDefault")
    @Expose
    private Integer isDefault;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer iD) {
        this.ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public Object getLine3() {
        return line3;
    }

    public void setLine3(Object line3) {
        this.line3 = line3;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNo1() {
        return phoneNo1;
    }

    public void setPhoneNo1(String phoneNo1) {
        this.phoneNo1 = phoneNo1;
    }

    public String getPhoneNo2() {
        return phoneNo2;
    }

    public void setPhoneNo2(String phoneNo2) {
        this.phoneNo2 = phoneNo2;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

}
