package com.tru2specs.android.util;

/**
 * Created by GP00471911 on 21-06-2017.
 */

public class Constants {
    public static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    public static final int STATUS_CODE_SUCCESS = 200;
    public static final String SOMETHING_WENT_WRONG_MSG = "Something went wrong!";
    public static final String KEY_USERDTO = "user_dto";
    public static final String KEY_LOGIN_SIGNUP_RESPONSE = "login_signup_response";
    public static final int STATUS_CODE_UNAUTHORIZED = 401;
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String LOGIN_TYPE_CUSTOMER = "customer";
    public static final String SOCIAL_MEDIA_TYPE_GOOGLE = "G";
    public static final String SOCIAL_MEDIA_TYPE_FACEBOOK = "F";
    public static final String SOCIAL_MEDIA_LOGIN_TRUE = "1";
    public static final String SOCIAL_MEDIA_LOGIN_FALSE = "0";
    //public static final String BASE_URL = "http://13.59.52.2:8000/";
    public static final String BASE_URL = "http://18.217.137.65:8000/";
    public static final String ADDRESS_ADDED_SUCCESSFULLY = "Address added successfully.";
    public static final String USER_TYPE_CUSTOMER = "Customer";
    public static final String CURRENCY = "RS ";
    public static final String PERCENCT_OFF = "% OFF";

//    public static final String BASE_URL = "http://192.168.2.11:1010/";

    //ngrok url
//    public static final String BASE_URL = "http://cc24cc88.ngrok.io/";
    public static int SNACKBAR_TIME_OUT = 3000;
    public static int STATUS_CODE_RECORD_NOT_FOUND = 204;

    public static final String EYEGLASSES = "EYEGLASSES";
    public static final String SUNGLASSES = "SUNGLASSES";
    public static final String CONTACT_LENSES = "CONTACT LENSES";
    public static final String PRODUCT_TYPE = "ProductType";

}
