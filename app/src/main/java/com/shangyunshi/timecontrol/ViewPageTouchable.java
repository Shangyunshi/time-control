package com.shangyunshi.timecontrol;

import android.content.Context;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class ViewPageTouchable extends ViewPager {
    public ViewPageTouchable(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
