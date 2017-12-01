package com.tru2specs.android.signup.presenter;

import com.tru2specs.android.objects.User;
import com.tru2specs.android.objects.request.SignUpRequest;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public interface ISignUpPresenter {
    boolean validateFields(SignUpRequest user);
    void attemptSignUpFormSubmission();
}
