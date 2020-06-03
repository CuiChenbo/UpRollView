package com.yhhc.ccb.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * ChenboCui 竖向的Viewpager
 */
public class VViewPager extends ViewPager {
 
    public VViewPager(Context context) {
        this(context, null);
    }
 
    public VViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(false, new DefaultTransformer());
    }
 
    private MotionEvent swapTouchEvent(MotionEvent event) {
        float width = getWidth();
        float height = getHeight();
 
        float swappedX = (event.getY() / height) * width;
        float swappedY = (event.getX() / width) * height;
 
        event.setLocation(swappedX, swappedY);
 
        return event;
    }
 
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = super.onInterceptTouchEvent(swapTouchEvent(event));
        //If not intercept, touch event should not be swapped.
        swapTouchEvent(event);
        return intercept;
    }
 
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapTouchEvent(ev));
    }
 
    class DefaultTransformer implements ViewPager.PageTransformer {
 
        @Override
        public void transformPage(View view, float position) {
//            float alpha = 0;
//            if (0 <= position && position <= 1) {
//                alpha = 1 - position;
//            } else if (-1 < position && position < 0) {
//                alpha = position + 1;
//            }
//            view.setAlpha(alpha);
            view.setTranslationX(view.getWidth() * -position);
            float yPosition = position * view.getHeight();
            view.setTranslationY(yPosition);
        }
    }
 
}
