package com.tru2specs.android.login.listerner;

import com.tru2specs.android.objects.responses.loginSignup.Data;
/**
 * Created by ajaykumarsantra on 03/07/17.
 */

public interface OnLoginListener {
    void onLoginSuccess(Data data);
    void onLoginFailure(String message);
}
