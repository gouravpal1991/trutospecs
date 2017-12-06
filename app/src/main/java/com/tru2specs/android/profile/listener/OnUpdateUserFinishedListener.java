package com.tru2specs.android.profile.listener;

import com.tru2specs.android.objects.responses.loginSignup.Data;

/**
 * Created by ajaykumarsantra on 20/08/17.
 */

public interface OnUpdateUserFinishedListener {
    void onUpdateUserSucces(Data data);
    void onUpdateUserFailure(String message);
}
