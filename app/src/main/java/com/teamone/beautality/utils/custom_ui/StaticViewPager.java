package com.teamone.beautality.utils.custom_ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by oshhepkov on 20.08.16.
 */

public class StaticViewPager extends ViewPager {
    private boolean mSwipeLocked = true;

    public StaticViewPager(Context context) {
        super(context);
    }

    public StaticViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !mSwipeLocked && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return !mSwipeLocked && super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        return !mSwipeLocked && super.canScrollHorizontally(direction);
    }

    public boolean getSwipeLocked() {
        return mSwipeLocked;
    }

    public void setSwipeLocked(boolean mSwipeLocked) {
        this.mSwipeLocked = mSwipeLocked;
    }
}