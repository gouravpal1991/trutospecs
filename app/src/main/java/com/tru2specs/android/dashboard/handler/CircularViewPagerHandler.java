package com.tru2specs.android.dashboard.handler;

import android.support.v4.view.ViewPager;

/**
 * Created by ajaykumarsantra on 05/07/17.
 */

public class CircularViewPagerHandler implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private int mCurrentPosition;
    private int mScrollState;
    private boolean scrollStateSetting;


    public CircularViewPagerHandler(final ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        handleScrollState(state);
        mScrollState = state;
    }

    private void handleScrollState(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            setNextItemIfNeeded();
        }
    }

    private void setNextItemIfNeeded() {
        if (!isScrollStateSetting()) {
            handleSetNextItem();
        }
    }

    private void handleSetNextItem() {
        final int lastPosition = mViewPager.getAdapter().getCount() - 1;
        if(mCurrentPosition==0){
            mViewPager.setCurrentItem(lastPosition,false);

        }else if(mCurrentPosition==lastPosition){
            mViewPager.setCurrentItem(0,false);
        }

    }

    public boolean isScrollStateSetting() {
        return mScrollState == ViewPager.SCROLL_STATE_SETTLING;
    }
}
