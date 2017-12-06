package com.tru2specs.android.search.view;

import com.tru2specs.android.objects.responses.productlisting.Product;

import java.util.List;

/**
 * Created by Selva
 */

public interface ISearchView {

    void setSearchResultProductsList(List<Product> productsList);
    void showProgress();
    void hideProgress();
}

