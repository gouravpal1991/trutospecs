package com.tru2specs.android.productslist.view;

import com.tru2specs.android.objects.responses.product.Data;
import com.tru2specs.android.objects.responses.product.Products;
import com.tru2specs.android.objects.responses.productlisting.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PB00471065 on 11/16/2017.
 */

public interface IProductListView {

    void hideProgressView();

    void showProgressView();

    void setProductList(List<Products> productList);

    void onFailure(String message);

    void onSuccess(Data data);

    void setToolbar();

}
