package com.tru2specs.android.profile.presenter;

import com.tru2specs.android.objects.request.UpdateUserRequest;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public interface IProfilePresenter {
    boolean validateFields(UpdateUserRequest user);
    void attempUpdateUser();
}
