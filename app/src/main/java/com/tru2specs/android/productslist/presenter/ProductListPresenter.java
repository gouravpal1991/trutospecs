package com.tru2specs.android.productslist.presenter;

import android.content.Context;

import com.tru2specs.android.login.listerner.OnLoginListener;
import com.tru2specs.android.login.model.LoginInteractor;
import com.tru2specs.android.manager.SessionManager;
import com.tru2specs.android.objects.request.LoginRequest;
import com.tru2specs.android.objects.responses.product.Data;
import com.tru2specs.android.objects.responses.product.Products;
import com.tru2specs.android.productlisting.OnProductListListener;
import com.tru2specs.android.productlisting.model.ProductListInteractor;
import com.tru2specs.android.productslist.view.IProductListView;
import com.tru2specs.android.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by PB00471065 on 11/16/2017.
 */

public class ProductListPresenter implements IProductListPresenter, OnProductListListener, OnLoginListener {

    private IProductListView mView;
    private ProductListInteractor mInteractor;
    private Context mContext;

    public ProductListPresenter(IProductListView view, Context context) {
        this.mView = view;
        this.mInteractor = new ProductListInteractor();
        this.mContext = context;
    }

    @Override
    public void getProductData() {
        mInteractor.getProductListData(this);
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
    public void onSuccess(Data data) {
        List<Products> productList = data.getProducts();

        mView.setProductList(productList);

        if (data != null) {
            mView.onSuccess(data);
        }
    }

    @Override
    public void onFailure(String message) {
        mView.onFailure(message);
    }

    @Override
    public void onLoginSuccess(com.tru2specs.android.objects.responses.loginSignup.Data data) {

    }

    @Override
    public void onLoginFailure(String message) {

    }
}
