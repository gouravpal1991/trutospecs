
package com.tru2specs.android.objects.responses.loginSignup;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName(value="PhoneNo", alternate={"ContactNo"})
    @Expose
    private String phoneNo;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("CustomerId")
    @Expose
    private String customerId;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Message")
    @Expose
    private String message;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phoneNo);
        dest.writeString(this.gender);
        dest.writeString(this.userId);
        dest.writeString(this.fullName);
        dest.writeString(this.customerId);
        dest.writeString(this.password);
        dest.writeString(this.message);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.phoneNo = in.readString();
        this.gender = in.readString();
        this.userId = in.readString();
        this.fullName = in.readString();
        this.customerId = in.readString();
        this.password = in.readString();
        this.message = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}