package com.tru2specs.android.profile.model;

import com.tru2specs.android.objects.request.SignUpRequest;
import com.tru2specs.android.objects.request.UpdateUserRequest;
import com.tru2specs.android.profile.listener.OnUpdateUserFinishedListener;
import com.tru2specs.android.signup.listener.OnSignUpFinishedListener;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public interface IProfileInteractor {
    void submitProfileUpdate(OnUpdateUserFinishedListener listener, UpdateUserRequest request);
}
