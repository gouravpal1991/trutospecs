package com.tru2specs.android.productlisting.view;

/**
 * Created by ajaykumarsantra on 04/07/17.
 */

public interface IProductListingView     {
    //method to set information like for whom the list is, banner image, title etc.
    //eg. MEN, title: 'WEAR YOUR STYLE'
    void setScreenDetails();
    void navigateToProductDetails();
    void setToolbar();
    void setProductsOnList();
    void showProgress();
    void hideProgress();

}
