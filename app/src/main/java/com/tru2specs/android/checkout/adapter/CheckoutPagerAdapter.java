package com.tru2specs.android.checkout.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by palgour on 8/26/17.
 */

public class CheckoutPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments;
    private Context mContext;

    public CheckoutPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragments=fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }
    @Override
    public int getCount() {
        return this.fragments.size();
    }
}
