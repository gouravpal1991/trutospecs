package com.tru2specs.android.login.presenter;

import com.tru2specs.android.objects.User;
import com.tru2specs.android.objects.request.LoginRequest;

/**
 * Created by ajaykumarsantra on 03/07/17.
 */

public interface ILoginPresenter {
    boolean validateFields(LoginRequest loginRequest);
    void attempToLogin();
}
