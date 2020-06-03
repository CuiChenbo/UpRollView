package com.yhhc.ccb.ui;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.yhhc.ccb.uprollview.R;

import java.util.ArrayList;

/**
 * CCB simple {@link Fragment} subclass.
 * Handler+动画  实现
 */
public class UpRollAnimFragment extends Fragment {


    public UpRollAnimFragment() {
        // Required empty public constructor
    }

private TextView tv;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_up_roll_anim , container , false);
        tv = view.findViewById(R.id.tv_roll_newsetad);
        init();
        return view;
    }


    private int TVADWITH = 111;
    private int TVADPOSITION = 0;
    private ArrayList<String> mList_Tv_Ad = new ArrayList<>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == TVADWITH){
                TvAnimation();
                TextViewRollAd();
            }
        }
    };

    private void init() {
        mList_Tv_Ad.add("七夕浪漫势头难挡，情人节当天再掀购花狂潮。");
        mList_Tv_Ad.add("学习同行业的经验叫创新.学习其他行业的经验叫革命");
        mList_Tv_Ad.add("无边落木萧萧下，不尽长江滚滚来。");
        mList_Tv_Ad.add("万里悲秋常作客，百年多病独登台。");
        mList_Tv_Ad.add("会当凌绝顶，一览众山小。");
        mList_Tv_Ad.add("此曲只应天上有，人间能得几回闻。");
        mList_Tv_Ad.add("国破山河在，城春草木深。感时花溅泪，恨别鸟惊心。");
        mList_Tv_Ad.add("安得广厦千万间，大庇天下寒士俱欢颜，风雨不动安如山！");
        mList_Tv_Ad.add("曾经沧海难为水，除却巫山不是云");
        TextViewRollAd();
    }

    private void TextViewRollAd() {
        TVADPOSITION++;

        handler.removeMessages(TVADWITH);
        handler.sendEmptyMessageDelayed(TVADWITH,2500);
    }


    private void TvAnimation() {

        TranslateAnimation downTranslateAnimation=new TranslateAnimation(0,0,0,-tv.getHeight());
        downTranslateAnimation.setDuration(500);
        downTranslateAnimation.setFillAfter(true);
        AlphaAnimation alphaAnimation=new AlphaAnimation(1,0);
        alphaAnimation.setDuration(500);

        AnimationSet animationSetOut=new AnimationSet(true);
        animationSetOut.addAnimation(downTranslateAnimation);
        animationSetOut.addAnimation(alphaAnimation);
        tv.startAnimation(animationSetOut);

        animationSetOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation){
                int iii = TVADPOSITION% mList_Tv_Ad.size();
                tv.setText(mList_Tv_Ad.get(iii));
                topTranslateAnimation();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
    private void topTranslateAnimation(){

        TranslateAnimation topTranslateAnimation=new TranslateAnimation(0,0,tv.getHeight(),0);
        topTranslateAnimation.setDuration(500);
        topTranslateAnimation.setFillAfter(true);

        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        alphaAnimation.setDuration(500);


        AnimationSet animationSetIn=new AnimationSet(true);
        animationSetIn.addAnimation(topTranslateAnimation);
        animationSetIn.addAnimation(alphaAnimation);

        tv.startAnimation(animationSetIn);

        animationSetIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation){
            }
        });
    }

}
