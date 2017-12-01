package com.tru2specs.android.productlisting;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tru2specs.android.objects.responses.product.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductListingViewPagerAdapter extends FragmentPagerAdapter {

    private List<Products> mProductList;
    private ArrayList<String> mProductTypes;

    public ProductListingViewPagerAdapter(FragmentManager fm, List<Products> productList) {
        super(fm);
        mProductList = productList;
        getProductTypesList();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ProductList",
                (ArrayList<? extends Parcelable>) getProductListByType(mProductList, mProductTypes.get(position)));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mProductTypes.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mProductTypes.get(position);
    }

    /**
     * Filtered list by productType
     *
     * @return
     */
    private List<Products> getProductListByType(List<Products> productList, String productType) {
        List<Products> listByProductType = new ArrayList<>();
        // Filter product list as per product type coming in response
        // Product Types are : EYEGLASSES , SUNGLASSES
        for (Products product : productList) {
            if (product.getProductType().equalsIgnoreCase(productType)) {
                listByProductType.add(product);
            }
        }
        return listByProductType;
    }

    private void getProductTypesList() {
        mProductTypes = new ArrayList<>();
        for (Products product : mProductList) {
            if (!mProductTypes.contains(product.getProductType())) {
                mProductTypes.add(product.getProductType());
            }
        }
    }
}
