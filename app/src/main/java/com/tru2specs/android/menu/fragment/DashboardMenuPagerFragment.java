package com.tru2specs.android.menu.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tru2specs.android.R;
import com.tru2specs.android.dashboard.adapter.DashboardCategoryListAdapter;
import com.tru2specs.android.dashboard.model.DrawerMenu;
import com.tru2specs.android.menu.adapter.DashboardMenuListAdapter;
import com.tru2specs.android.menu.model.MenuItem;
import com.tru2specs.android.objects.responses.dashboard.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by GP00471911 on 29-06-2017.
 */

public class DashboardMenuPagerFragment extends Fragment {

    private static final String KEY_CATEGORIES = "categories";
    private static final String KEY_CATEGORY_FOR = "category_for";
    @BindView(R.id.rv_category)
    RecyclerView mRvCategory;
    Unbinder unbinder;
    private DashboardMenuListAdapter mAdapter;

    public DashboardMenuPagerFragment() {
        // Required empty public constructor
    }

    public static DashboardMenuPagerFragment newInstance(MenuItem menuItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_CATEGORIES, (Parcelable) menuItem);

        DashboardMenuPagerFragment simplePageFragment = new DashboardMenuPagerFragment();
        simplePageFragment.setArguments(bundle);
        return simplePageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard_category_pager, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(KEY_CATEGORIES)) {
            setCategoriesView((MenuItem) getArguments().getParcelable(KEY_CATEGORIES));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setCategoriesView(MenuItem menuItem) {
        mAdapter = new DashboardMenuListAdapter(getActivity(), menuItem);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRvCategory.setLayoutManager(mLayoutManager);

//        mRvCategory.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        mRvCategory.setItemAnimator(new DefaultItemAnimator());
        mRvCategory.setAdapter(mAdapter);

    }
}
