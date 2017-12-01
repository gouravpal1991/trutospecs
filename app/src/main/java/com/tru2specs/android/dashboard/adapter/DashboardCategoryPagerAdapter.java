package com.tru2specs.android.dashboard.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tru2specs.android.dashboard.fragment.DashboardCategoryPagerFragment;
import com.tru2specs.android.objects.responses.dashboard.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GP00471911 on 29-06-2017.
 */

public class DashboardCategoryPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public DashboardCategoryPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Type categoriesList) {
        mFragmentList.add(DashboardCategoryPagerFragment.newInstance(categoriesList));
        mFragmentTitleList.add(categoriesList.getProductType());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
