package com.tru2specs.android.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tru2specs.android.dashboard.model.DrawerMenu;
import com.tru2specs.android.menu.fragment.DashboardMenuPagerFragment;
import com.tru2specs.android.menu.model.MenuItem;
import com.tru2specs.android.objects.responses.dashboard.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GP00471911 on 29-06-2017.
 */

public class DashboardMenuPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public DashboardMenuPagerAdapter(FragmentManager manager) {
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

    public void addFragment(MenuItem menuItem) {
        mFragmentList.add(DashboardMenuPagerFragment.newInstance(menuItem));
        mFragmentTitleList.add(menuItem.getCategoryName());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
