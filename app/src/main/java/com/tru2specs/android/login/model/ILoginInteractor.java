package com.tru2specs.android.login.model;

import com.tru2specs.android.login.listerner.OnLoginListener;
import com.tru2specs.android.objects.User;
import com.tru2specs.android.objects.request.LoginRequest;

/**
 * Created by ajaykumarsantra on 03/07/17.
 */

public interface ILoginInteractor {
    void submitLogin(OnLoginListener listener,LoginRequest loginRequest);
}
