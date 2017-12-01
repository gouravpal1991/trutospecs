package com.tru2specs.android.basicinfo.model;

import android.content.Context;

import com.tru2specs.android.basicinfo.listener.OnBasicInfoFinishedListener;
import com.tru2specs.android.manager.SessionManager;
import com.tru2specs.android.objects.User;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public class BasicInfoInteractor implements IBasicInfoInteractor {
    @Override
    public void submitBasicInfo(Context context, OnBasicInfoFinishedListener listener, User user) {
        new SessionManager(context).setBasicInfo(user.getFullName(), user.getContactNo());
        listener.onBasicInfoSuccess();
    }
}
