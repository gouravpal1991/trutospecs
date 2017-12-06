package com.tru2specs.android.manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tru2specs.android.basicinfo.BasicInfoActivity;
import com.tru2specs.android.objects.responses.loginSignup.Data;
import com.tru2specs.android.util.Constants;

import java.util.HashMap;

/**
 * Created by gouravpal on 09/04/17.
 */

public class SessionManager {
    private static final String IS_BASIC_INFO_STORED = "IsBasicInfoStored";
    private SharedPreferences mPref;
    private static final String APP_PREFERNCE = "ecommap_pref";
    private static final String IS_FIRST_TIME_VISIT = "isFirstTimeVisit";
    private static final String IS_LOGIN = "IsLoggedIn";

    SharedPreferences.Editor mEditor;
    Context mContext;

    public SessionManager(Context context) {
        this.mContext = context;
        mPref = mContext.getSharedPreferences(APP_PREFERNCE, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public void saveBoardingSuccessfull() {
        mEditor.putBoolean(IS_FIRST_TIME_VISIT, true);
        mEditor.commit();
    }

    public boolean isFirstTimeVisit() {
        return mPref.getBoolean(IS_FIRST_TIME_VISIT, false);
    }


    public void logoutUser() {
        // Clearing all data from Shared Preferences
        mEditor.clear();
        mEditor.commit();

        // After logout redirect user to BasicInfo Activity
        Intent i = new Intent(mContext, BasicInfoActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring BasicInfo Activity
        mContext.startActivity(i);

    }

    // Get Login State
    public boolean isLoggedIn() {
        return mPref.getBoolean(IS_LOGIN, false);
    }

    public void createLoginSession(Data loginSignupResponseData) {
        String loginSignUpResponse = new Gson().toJson(loginSignupResponseData);
        mEditor.putBoolean(IS_LOGIN, true);
        mEditor.putString(Constants.KEY_LOGIN_SIGNUP_RESPONSE, loginSignUpResponse);
        mEditor.commit();
    }

    //get user info stored in preferences
    public String getUserInfo() {
        return mPref.getString(Constants.KEY_LOGIN_SIGNUP_RESPONSE, null);
    }

    //saving user credentials in shared preferences.
    public void setUserCredentials(String username, String password) {
        mEditor.putBoolean(IS_LOGIN, true);
        mEditor.putString(Constants.KEY_USERNAME, username);
        mEditor.putString(Constants.KEY_PASSWORD, password);
        mEditor.commit();
    }

    //get user credentials
    public HashMap<String, String> getUserCredentials() {
        HashMap<String, String> usercredentials = new HashMap<String, String>();
        usercredentials.put(Constants.KEY_USERNAME, mPref.getString(Constants.KEY_USERNAME, null));
        usercredentials.put(Constants.KEY_PASSWORD, mPref.getString(Constants.KEY_PASSWORD, null));
        return usercredentials;
    }


    //get basic info stored in preferences
    public HashMap<String, String> getBasicInfo() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(BasicInfoActivity.USER_KEY_FULLNAME, mPref.getString(BasicInfoActivity.USER_KEY_FULLNAME, null));
        user.put(BasicInfoActivity.USER_KEY_CONTACT, mPref.getString(BasicInfoActivity.USER_KEY_CONTACT, null));
        return user;
    }


    public void setBasicInfo(String username, String password) {
        mEditor.putBoolean(IS_BASIC_INFO_STORED, true);
        mEditor.putString(BasicInfoActivity.USER_KEY_FULLNAME, username);
        mEditor.putString(BasicInfoActivity.USER_KEY_CONTACT, password);
        mEditor.commit();

    }

    public boolean isBasicInfoStored() {
        return mPref.getBoolean(IS_BASIC_INFO_STORED, false);
    }
}
