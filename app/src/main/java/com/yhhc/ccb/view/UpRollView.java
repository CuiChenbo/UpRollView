package com.yhhc.ccb.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ViewFlipper;


import com.yhhc.ccb.uprollview.R;

import java.util.List;

public class UpRollView extends ViewFlipper {
 private Context content;
    public UpRollView(Context context) {
        super(context);
        init(context);
    }
    private int Interval = 3000;

    public UpRollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.content = context;
        setFlipInterval(Interval);//设置时间间隔
        setInAnimation(context, R.anim.in);
        setOutAnimation(context, R.anim.out);
    }
    public void setInterval(int i){
        Interval = i;
        setFlipInterval(Interval);//设置时间间隔
    }

    /**
     * 设置循环滚动的View数组
     */
    public void setViews(final List<View> views) {
        if (views == null || views.size() == 0) return;
        removeAllViews();
        for ( int i = 0; i < views.size(); i++) {
            final int finalposition=i;
            //设置监听回调
            views.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(finalposition, views.get(finalposition));
                    }
                }
            });
            addView(views.get(i));
        }
        startFlipping();
    }
    
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }
}