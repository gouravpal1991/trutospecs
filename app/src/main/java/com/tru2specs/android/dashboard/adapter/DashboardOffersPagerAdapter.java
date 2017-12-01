package com.tru2specs.android.dashboard.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tru2specs.android.dashboard.fragment.DashboardOfferPagerFragment;

import java.util.ArrayList;

/**
 * Created by GP00471911 on 24-03-2017.
 */

public class DashboardOffersPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<String> mImages = new ArrayList<>();


    public DashboardOffersPagerAdapter(FragmentManager fm, ArrayList<String> imageUrls) {
        super(fm);
        this.mImages = imageUrls;
    }

    @Override
    public Fragment getItem(int position) {

        return DashboardOfferPagerFragment.getInstance(mImages.get(position));
    }

    @Override
    public int getCount() {
        return mImages.size();
    }
}

