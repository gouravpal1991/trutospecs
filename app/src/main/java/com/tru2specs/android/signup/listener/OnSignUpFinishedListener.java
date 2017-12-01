package com.tru2specs.android.signup.listener;

import com.tru2specs.android.objects.responses.loginSignup.Data;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public interface OnSignUpFinishedListener {
    void onSignUpSuccess(Data data);
    void onSignUpFailure(String message);
}
