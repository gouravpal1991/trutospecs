package com.tru2specs.android.basicinfo.presenter;

import com.tru2specs.android.objects.User;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public interface IBasicInfoPresenter {
    boolean validateFields(User user);
    void attemptBasicInfoSubmission();
}
