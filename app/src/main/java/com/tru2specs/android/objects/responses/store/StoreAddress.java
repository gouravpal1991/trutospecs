
package com.tru2specs.android.objects.responses.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreAddress {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("Line1")
    @Expose
    private String addressLine1;


    @SerializedName("Line2")
    @Expose
    private String addressLine2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("Pincode")
    @Expose
    private String pincode;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("LandlineNo")
    @Expose
    private String contact;
    @SerializedName("StoreName")
    private String store;
    @SerializedName("MobileNo")
    private String mobileNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }



}
