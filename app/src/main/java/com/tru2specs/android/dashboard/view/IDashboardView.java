package com.tru2specs.android.dashboard.view;
import com.tru2specs.android.objects.responses.dashboard.DashboardResponse;
import com.tru2specs.android.objects.responses.dashboard.OfferHeader;
import com.tru2specs.android.objects.responses.dashboard.Type;
import com.tru2specs.android.objects.responses.loginSignup.Data;

import java.util.List;

/**
 * Created by GP00471911 on 28-06-2017.
 */

public interface IDashboardView {
    void navigateToSearchActivity();
    void navigateToCartActivity();
    void navigateToProfileActivity();
    void navigateToProductListing(String menOrWomen);
    void onFailure(String message);
    void onSuccess(DashboardResponse response);
    void setCategoryPager(List<Type> categoriesList);
    void setOffersPager(List<OfferHeader> offers);
    void showListOfStores();
    void navigateToLogin();
    void navigateToSignUp();
    void hideProgressView();
    void showProgressView();
    void setData(Data data);
    void setBannerAdImage();
    void setMenuItems();
    void setProductList(List<Type> type);
    void setupViewpager(List<OfferHeader> offers);
}
