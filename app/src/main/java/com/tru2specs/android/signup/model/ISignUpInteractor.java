package com.tru2specs.android.signup.model;

import com.tru2specs.android.objects.User;
import com.tru2specs.android.objects.request.SignUpRequest;
import com.tru2specs.android.signup.listener.OnSignUpFinishedListener;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public interface ISignUpInteractor {
    void submitSignUp(OnSignUpFinishedListener listener, SignUpRequest request);
}
