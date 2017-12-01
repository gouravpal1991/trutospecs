package com.tru2specs.android.objects.request;

/**
 * Created by ajaykumarsantra on 11/07/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpRequest implements Parcelable {

    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("ContactNo")
    @Expose
    private String contactNo;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("ReferralCode")
    @Expose
    private String referralCode;
    @SerializedName("IsSocial")
    @Expose
    private String isSocialMedia;
    @SerializedName("SocialMediaAccessToken")
    @Expose
    private String socialMediaAccessToken;
    @SerializedName("SocialMediaType")
    @Expose
    private String socialMediaType;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("LoginType")
    @Expose
    private String loginType;
    @SerializedName("AccessToken")
    @Expose
    private String accessToken;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getIsSocialMedia() {
        return isSocialMedia;
    }

    public void setIsSocialMedia(String isSocialMedia) {
        this.isSocialMedia = isSocialMedia;
    }

    public String getSocialMediaAccessToken() {
        return socialMediaAccessToken;
    }

    public void setSocialMediaAccessToken(String socialMediaAccessToken) {
        this.socialMediaAccessToken = socialMediaAccessToken;
    }

    public String getSocialMediaType() {
        return socialMediaType;
    }

    public void setSocialMediaType(String socialMediaType) {
        this.socialMediaType = socialMediaType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.contactNo);
        dest.writeString(this.gender);
        dest.writeString(this.referralCode);
        dest.writeString(this.isSocialMedia);
        dest.writeString(this.socialMediaAccessToken);
        dest.writeString(this.socialMediaType);
        dest.writeString(this.createdBy);
        dest.writeString(this.loginType);
        dest.writeString(this.accessToken);
    }

    public SignUpRequest() {
    }

    protected SignUpRequest(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.contactNo = in.readString();
        this.gender = in.readString();
        this.referralCode = in.readString();
        this.isSocialMedia = in.readString();
        this.socialMediaAccessToken = in.readString();
        this.socialMediaType = in.readString();
        this.createdBy = in.readString();
        this.loginType = in.readString();
        this.accessToken = in.readString();
    }

    public static final Parcelable.Creator<SignUpRequest> CREATOR = new Parcelable.Creator<SignUpRequest>() {
        @Override
        public SignUpRequest createFromParcel(Parcel source) {
            return new SignUpRequest(source);
        }

        @Override
        public SignUpRequest[] newArray(int size) {
            return new SignUpRequest[size];
        }
    };

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}