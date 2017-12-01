package com.tru2specs.android.store.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by GP00471911 on 28-06-2017.
 */


public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    GestureDetector mGestureDetector;
    ClickListener mClickListener;

    public RecyclerTouchListener(Context context, final RecyclerView mRecyclerView, ClickListener listener) {
        this.mClickListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mClickListener != null) {
                    mClickListener.onLongClick(child,mRecyclerView.getChildPosition(child));
                }
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child=rv.findChildViewUnder(e.getX(),e.getY());
        if(child!=null && mClickListener!=null && mGestureDetector.onTouchEvent(e)){
            mClickListener.onClick(child,rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

}
