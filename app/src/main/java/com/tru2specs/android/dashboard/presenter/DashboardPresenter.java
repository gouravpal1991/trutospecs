package com.tru2specs.android.dashboard.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.tru2specs.android.dashboard.listener.OnDashboardListener;
import com.tru2specs.android.dashboard.model.DashboardInteractor;
import com.tru2specs.android.dashboard.view.IDashboardView;
import com.tru2specs.android.login.listerner.OnLoginListener;
import com.tru2specs.android.login.model.LoginInteractor;
import com.tru2specs.android.manager.SessionManager;
import com.tru2specs.android.objects.request.LoginRequest;
import com.tru2specs.android.objects.responses.dashboard.DashboardResponse;
import com.tru2specs.android.objects.responses.dashboard.OfferHeader;
import com.tru2specs.android.objects.responses.dashboard.TagCategory;
import com.tru2specs.android.objects.responses.dashboard.Type;
import com.tru2specs.android.objects.responses.loginSignup.Data;
import com.tru2specs.android.util.Constants;

import java.util.HashMap;
import java.util.List;

/**
 * Created by GP00471911 on 29-06-2017.
 */

public class DashboardPresenter implements IDashboardPresenter, OnDashboardListener, OnLoginListener {

    private IDashboardView mView;
    private DashboardInteractor mInteractor;
    private Context mContext;

    public DashboardPresenter(IDashboardView view, Context context) {
        this.mView = view;
        this.mInteractor = new DashboardInteractor();
        this.mContext = context;
    }

    @Override
    public void getDashboardData() {
        mInteractor.getDashboardData(this);
    }

    @Override
    public void doLogin() {

        HashMap<String, String> userCredentials = new SessionManager(mContext).getUserCredentials();
        LoginRequest request = new LoginRequest();
        request.setEmail(userCredentials.get(Constants.KEY_USERNAME));
        request.setPassword(userCredentials.get(Constants.KEY_PASSWORD));

        new LoginInteractor()
                .submitLogin(this, request);


    }

    @Override
    public void onSuccess(com.tru2specs.android.objects.responses.dashboard.Data data) {
//        List<OfferHeader> offers = data.getOfferHeaders();
//        if (offers != null & offers.size() > 0) {
//            mView.setOffersPager(offers);
//        }
List<OfferHeader> headers = data.getBanner();
        if(headers!=null && headers.size()>0){
            mView.setupViewpager(headers);
        }
        List<Type> productList = data.getType();
        mView.setProductList(productList);
//        if (categoriesList != null & categoriesList.size() > 0) {
//            mView.setCategoryPager(categoriesList);
//        }

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onLoginSuccess(Data data) {
        mView.setData(data);
    }

    @Override
    public void onLoginFailure(String message) {
        String previousStoredResponseJSON = new SessionManager(mContext).getUserInfo();
        if (!TextUtils.isEmpty(previousStoredResponseJSON)) {
            Data previouslyStoredResponse = new Gson().fromJson(new SessionManager(mContext).getUserInfo(), Data.class);
            mView.setData(previouslyStoredResponse);
        }
    }
}
