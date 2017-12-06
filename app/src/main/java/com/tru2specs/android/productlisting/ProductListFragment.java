package com.tru2specs.android.productlisting;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tru2specs.android.R;
import com.tru2specs.android.objects.responses.product.Data;
import com.tru2specs.android.objects.responses.product.Products;
import com.tru2specs.android.productslist.presenter.ProductListPresenter;
import com.tru2specs.android.productslist.view.IProductListView;
import com.tru2specs.android.util.Constants;

import java.util.List;

public class ProductListFragment extends Fragment {

    private RecyclerView mProductRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.product_list_fragment, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProductRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mProductRecyclerView.setLayoutManager(layoutManager);
        List<Products> productList = getArguments().getParcelableArrayList("ProductList");
        ProductRecyclerViewAdapter productAdapter = new ProductRecyclerViewAdapter(getActivity(), productList);
        mProductRecyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }


}