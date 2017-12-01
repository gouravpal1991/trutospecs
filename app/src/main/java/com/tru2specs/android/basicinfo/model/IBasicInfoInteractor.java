package com.tru2specs.android.basicinfo.model;

import android.content.Context;

import com.tru2specs.android.basicinfo.listener.OnBasicInfoFinishedListener;
import com.tru2specs.android.objects.User;

/**
 * Created by GP00471911 on 19-06-2017.
 */

public interface IBasicInfoInteractor {
    void submitBasicInfo(Context context, OnBasicInfoFinishedListener listener, User user);
}
